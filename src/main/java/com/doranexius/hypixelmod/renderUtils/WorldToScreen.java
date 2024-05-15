package com.doranexius.hypixelmod.renderUtils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector4f;

public class WorldToScreen {
	
	public static Matrix4f viewMatrix;
	public static Matrix4f projectionMatrix;
	
	
	public static Vector2f projectWorldToScreen(Vector4f coords) {
		Matrix4f viewProjectionMatrix = new Matrix4f();
		Matrix4f.mul(viewMatrix, projectionMatrix, viewProjectionMatrix);
		
		Vector4f projectedCoords = new Vector4f();
		Matrix4f.transform(viewProjectionMatrix, coords, projectedCoords);
		
		projectedCoords.x /= projectedCoords.w;
		projectedCoords.y /= projectedCoords.w;
		
		float ndcX = (projectedCoords.x + 1.0f) * 0.5f;
	    float ndcY = (projectedCoords.y + 1.0f) * 0.5f;
		
		return new Vector2f(ndcX, ndcY);
	}
	
}
