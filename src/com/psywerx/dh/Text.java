package com.psywerx.dh;

public class Text extends Drawable {
    
    Square[] squares;
    
    public Text(String s){
        char[] chars = s.toCharArray();
        squares = new Square[chars.length];
        size = new float[]{0.05f,0.05f};
        for(int i = 0; i < chars.length; i++){
            Square sq = new Square();
            sq.texture.enabled = true;
            sq.texture.setSpriteFromChar(chars[i]);
            sq.size = size;
            sq.position[0] += i*size[0]*1.05f;
            sq.position[2] += i*0.001f;
            
            // Adding from end to start so that the first char is being drawn last.
            squares[chars.length-i-1] = sq;
        }
    }
    
    public void update(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < squares.length; i++) {
            squares[chars.length-i-1].texture.setSpriteFromChar(chars[i]);
        }
    }
    public void setSize(float[] size){
        for (int i = 0; i < squares.length; i++) {
            Square sq = squares[i];
            sq.size = size;
            sq.position[0] = squares[0].position[0] - i*size[0]*1.05f;
        }
        this.size = size;
    }
    public void move(float[] position) {
        for (int i = 0; i < squares.length; i++) {
            Square sq = squares[i];
            sq.position[0] += position[0];
            sq.position[1] += position[1];
            sq.position[2] += position[2];
        }
        this.position = squares[0].position;
    }

    @Override
    public void tick(float theta){
        
    }
    @Override
    public void draw(){
        for (Square s : squares) {
            s.draw();
        }
    }

}
