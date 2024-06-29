package com.doranexius.hypixelmod.gui;

import com.doranexius.hypixelmod.gui.buttons.NewCategoryButton;
import com.doranexius.hypixelmod.gui.buttons.ToggleButton;
import com.doranexius.hypixelmod.gui.elements.DropdownToggle;
import com.doranexius.hypixelmod.gui.elements.ModToggleButton;
import com.doranexius.hypixelmod.modules.Category;
import com.doranexius.hypixelmod.modules.Module;
import com.doranexius.hypixelmod.modules.ModuleManager;
import com.doranexius.hypixelmod.overlays.InfoOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NewMainGUI extends GuiScreen {

    private final ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());
    private final int centerX = this.res.getScaledWidth() / 2;
    private final int centerY = this.res.getScaledHeight() / 2;

    private final int outlineX1 = centerX - 175;
    private final int outlineY1 = centerY - 120;
    private final int outlineX2 = outlineX1 + 350;
    private final int outlineY2 = outlineY1 + 240;

    private final int menuX1 = outlineX1 + 2;
    private final int menuY1 = outlineY1 + 2;
    private final int menuX2 = outlineX2 - 2;
    private final int menuY2 = outlineY2 - 2;

    private final int categoryPanelWidth = 75;

    private List<GuiButton> guiButtons = new ArrayList<>();
    private int toggleButtonStartIndex;

    private Category currCategory = Category.RENDER;
    private GuiButton lastButton = null;

    private int id = 1;

    private List<ModToggleButton> modToggleButtons = new ArrayList<>();
    private List<DropdownToggle> dropdownToggles = new ArrayList<>();

    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        int startY = 55;
        for (Category category : Category.values()) {
            String text = StringUtils.capitalize(category.toString().toLowerCase());
            NewCategoryButton button = new NewCategoryButton(id, menuX1 + categoryPanelWidth / 2 - (Minecraft.getMinecraft().fontRendererObj.getStringWidth(text) + 10) / 2, menuY1 + startY, text, category);
            if (category == Category.RENDER) {
                button.enable();
                lastButton = button;
            }
            buttonList.add(button);
            startY += 25;
            id += 1;
        }
        toggleButtonStartIndex = id;

        int containerStartY = menuY1 + 20;
        int containerXInc = -115;
        Category lastCategory = null;
        for (int i = 0; i < ModuleManager.getModList().size(); i++) {
            Module module = ModuleManager.getModList().get(i);
            Category moduleCategory = module.getCategory();
            if (i % 2 == 0 && i != 0) {
                containerStartY += 30;
            }

            if (lastCategory != null && moduleCategory != lastCategory) {
                containerStartY = menuY1 + 20;
                containerXInc = -115;
            }

            DropdownToggle dropdownToggle = new DropdownToggle(this.id, (menuX1 + categoryPanelWidth + menuX2) / 2 + containerXInc, containerStartY, module);
            dropdownToggles.add(dropdownToggle);
            this.id += module.getOptions().size() + 1;

            ModToggleButton modToggleButton = new ModToggleButton(this.id, (menuX1 + categoryPanelWidth + menuX2) / 2 + containerXInc, containerStartY, module);
            modToggleButtons.add(modToggleButton);
            this.id += 1;

            containerXInc = containerXInc == -115 ? 15 : -115;
            lastCategory = moduleCategory;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawRect(outlineX1-2, outlineY1-2, outlineX2+2, outlineY2+2, 0x22000000);
        drawRect(outlineX1, outlineY1, outlineX2, outlineY2, 0x44000000);
        drawRect(menuX1, menuY1, menuX1 + categoryPanelWidth, menuY2, 0xFF000000);
        drawGradientRect(menuX1 + categoryPanelWidth, menuY1, menuX2, menuY2, 0xFF323D3E, 0xFF000000);

        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Hypixel", menuX1 + categoryPanelWidth / 2, menuY1 + 15, InfoOverlay.getCurrentGradientColor());
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "Mod", menuX1 + categoryPanelWidth / 2, menuY1 + 25, InfoOverlay.getCurrentGradientColor());
        drawHorizontalLine(menuX1 + categoryPanelWidth / 2 - 10, menuX1 + categoryPanelWidth / 2 + 10, menuY1 + 45, 0xFFFFFFFF);

        this.drawCategory(currCategory);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button instanceof NewCategoryButton) {
            ((NewCategoryButton) button).enable();
            if (lastButton != null && !lastButton.equals(button)) {
                ((NewCategoryButton) lastButton).disable();
            }
            lastButton = button;
            currCategory = ((NewCategoryButton) button).getCategory();
        } else if (button instanceof DropdownToggle) {
            DropdownToggle dropButton = (DropdownToggle) button;
            dropButton.toggle();
            int index = this.dropdownToggles.indexOf(dropButton);
            boolean isLeft = index % 2 == 0;
            if (dropButton.isToggled()) {
                for (int i = 0; i < this.dropdownToggles.size(); i++) {
                    DropdownToggle dropdownToggle = this.dropdownToggles.get(i);
                    ModToggleButton modToggleButton = this.modToggleButtons.get(i);
                    if (((isLeft && i % 2 == 0) || (!isLeft && i % 2 != 0)) && dropdownToggle.getCategory().equals(dropButton.getCategory()) && i > this.dropdownToggles.indexOf(dropButton) && !dropdownToggle.equals(dropButton)) {
                        dropdownToggle.updateY(true, dropButton.getOptionsLength());
                        modToggleButton.updateY(true, dropButton.getOptionsLength());
                    }
                }
            } else {
                buttonList.removeAll(dropButton.getButtons());

                for (DropdownToggle dropdownToggle : this.dropdownToggles) {
                    dropdownToggle.updateY(false, dropButton.getOptionsLength());
                }

                for (ModToggleButton modToggleButton : this.modToggleButtons) {
                    modToggleButton.updateY(false, dropButton.getOptionsLength());
                }
            }


        } else if (button instanceof ModToggleButton) {
            ModToggleButton modToggleButton = (ModToggleButton) button;
            modToggleButton.toggle();
        } else if (button instanceof ToggleButton) {
            ToggleButton toggleButton = (ToggleButton) button;
            toggleButton.toggle();
        }
    }

    private void drawCategory(Category category) {
        this.clearModuleButtons();

        for (DropdownToggle dropdownToggle : dropdownToggles) {
            if (dropdownToggle.getCategory().equals(category)) {
                buttonList.add(dropdownToggle);
            }

            if (dropdownToggle.isToggled() && dropdownToggle.getCategory().equals(category)) {
                buttonList.addAll(dropdownToggle.getButtons());
            }
        }

        for (ModToggleButton item : modToggleButtons) {
            if (item.getCategory().equals(category)) {
                buttonList.add(item);
            }
        }
    }

    private void clearModuleButtons() {
        this.buttonList = this.buttonList.stream().filter(button -> button instanceof NewCategoryButton).collect(Collectors.toList());
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
