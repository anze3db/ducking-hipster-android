package com.psywerx.dh;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Square extends Drawable {

    private float[] modelMatrix = new float[16];
    private ByteBuffer vbb;
    private FloatBuffer vertBuffer;
    private FloatBuffer colorBuffer;
    private FloatBuffer texBuffer;
    private GlProgram p = Game.program;
    public float[] rot = new float[]{0f, 0f, 1f, 0f};
    private float[] colB;
    float[] verticesStart = new float[] { 1.0f, -1.0f, 0.0f, -1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, -1.0f, 1.0f, 0.0f };
    float[] vertices = new float[12];
    

    Square() {
        Matrix.setIdentityM(modelMatrix, 0);

        vbb = ByteBuffer.allocateDirect(3 * 4 * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertBuffer = vbb.asFloatBuffer();

        vbb = ByteBuffer.allocateDirect(4 * 4 * 4);
        vbb.order(ByteOrder.nativeOrder());
        colorBuffer = vbb.asFloatBuffer();

        vbb = ByteBuffer.allocateDirect(2 * 4 * 4);
        vbb.order(ByteOrder.nativeOrder());
        texBuffer = vbb.asFloatBuffer();
        colB = new float[] { color[0], color[1], color[2], color[3], color[0], color[1], color[2], color[3],
                color[0], color[1], color[2], color[3], color[0], color[1], color[2], color[3] };
    }

    @Override
    public void tick(float theta) {

    }

    @Override
    public void draw() {
        Matrix.setIdentityM(modelMatrix, 0);
        Matrix.translateM(modelMatrix, 0, modelMatrix, 0, position[0], position[1], position[2]);
        Matrix.rotateM(modelMatrix, 0, rot[0], rot[1], rot[2], rot [3]);
        GLES20.glUniformMatrix4fv(p.modelMatrixLoc, 1, false, modelMatrix, 0);

        for (int i = 0; i < vertices.length; i += 3) {
            vertices[i] = verticesStart[i] * size[0];
            vertices[i + 1] = verticesStart[i + 1] * size[1];
        }
        vertBuffer.put(vertices);
        vertBuffer.flip();

        GLES20.glVertexAttribPointer(p.positionLoc, 3, GLES20.GL_FLOAT, false, 0, vertBuffer);
        GLES20.glEnableVertexAttribArray(p.positionLoc);

        colorBuffer.put(colB);
        colorBuffer.flip();

        GLES20.glVertexAttribPointer(p.colorLoc, 4, GLES20.GL_FLOAT, false, 0, colorBuffer);
        GLES20.glEnableVertexAttribArray(p.colorLoc);

        texBuffer.put(texture.getTextureUV());
        texBuffer.flip();

        GLES20.glVertexAttribPointer(p.texLoc, 2, GLES20.GL_FLOAT, false, 0, texBuffer);
        GLES20.glEnableVertexAttribArray(p.texLoc);

        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, p.texture);

        GLES20.glUniform1f(p.isTextLoc, texture.enabled ? 1.0f : 0.0f);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_STRIP, 0, 4);

        GLES20.glDisableVertexAttribArray(p.positionLoc);
        GLES20.glDisableVertexAttribArray(p.colorLoc);
        GLES20.glDisableVertexAttribArray(p.texLoc);
    }

}
