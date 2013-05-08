package com.psywerx.dh;

import java.io.InputStream;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

public class GlProgram {

    protected int program;
    protected int positionLoc;
    protected int colorLoc;
    protected int texLoc;
    protected int projectionMatrixLoc;
    protected int modelMatrixLoc;
    protected int samplerLoc;
    protected int isTextLoc;
    private final int[] textSets = new int[]{R.drawable.text, R.drawable.text1, R.drawable.text2};
    protected int[] textures = new int[textSets.length];
    
    private Context context;

    public GlProgram(Context context){
        
        this.context = context;
        
        program = GLES20.glCreateProgram();
        
        int vertShader = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        int fragShader = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);
        
        createShader(vertShader, readRaw(R.raw.vertex));
        createShader(fragShader, readRaw(R.raw.fragment));
        
        GLES20.glLinkProgram(program);
        
        positionLoc = GLES20.glGetAttribLocation(program, "attribute_Position");
        colorLoc    = GLES20.glGetAttribLocation(program, "attribute_Color");
        texLoc      = GLES20.glGetAttribLocation(program, "a_texCoord");

        projectionMatrixLoc = GLES20.glGetUniformLocation(program, "uniform_Projection");
        modelMatrixLoc      = GLES20.glGetUniformLocation(program, "uniform_Model");
        samplerLoc          = GLES20.glGetUniformLocation(program, "s_texture");
        isTextLoc           = GLES20.glGetUniformLocation(program, "u_isText");
        for (int i = 0; i < textSets.length; i++ ) {
          textures[i] = loadTexture(textSets[i]); 
        }
    }
    private String readRaw(int id){
        try {
            Resources res = context.getResources();
            InputStream in_s = res.openRawResource(id);

            byte[] b = new byte[in_s.available()];
            in_s.read(b);
            return new String(b);
        } catch (Exception e) {
            Log.w("dh", "Shader code could not be read");
            return "";
        }
    }
    private int loadTexture(int textureResource) {
        int[] texture = {0};
        GLES20.glPixelStorei(GLES20.GL_UNPACK_ALIGNMENT, 1);
        GLES20.glGenTextures(1, texture, 0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, texture[0]);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        Bitmap image = BitmapFactory.decodeResource(context.getResources(), textureResource, options);
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, image, 0);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_LINEAR_MIPMAP_NEAREST);
        GLES20.glGenerateMipmap(GLES20.GL_TEXTURE_2D);
        return texture[0];
    }

    private void createShader(int shader, String source) {
        GLES20.glShaderSource(shader, source);
        GLES20.glCompileShader(shader);
        GLES20.glAttachShader(program, shader);
        int[] compiled = new int[1];
        GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0);
        if (compiled[0] == 0) {
            Log.e("Shader", "Could not compile shader " + shader + ":");
            Log.e("Shader", GLES20.glGetShaderInfoLog(shader));
            GLES20.glDeleteShader(shader);
            shader = 0;
        }
    }
}
