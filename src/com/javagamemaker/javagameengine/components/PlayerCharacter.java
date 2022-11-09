package com.javagamemaker.javagameengine.components;

import com.javagamemaker.javagameengine.JavaGameEngine;
import com.javagamemaker.javagameengine.input.Input;
import com.javagamemaker.javagameengine.input.Keys;
import com.javagamemaker.javagameengine.msc.Vector2;

public class PlayerCharacter extends Component{
    PhysicsBody body = new PhysicsBody();

    public PlayerCharacter(PhysicsBody body){
        this.body = body;
    }

    @Override
    public void update() {
        super.update();
        if(Input.isKeyDown(Keys.A)){
            getFirstParent().rotate(-1);            //body.setRotationalPoint(new Vector2(0,0));
        }
        if(Input.isKeyDown(Keys.D)){
            //body.setRotationalForce(0.9f);
            getFirstParent().rotate(1);
            //   body.setRotationalPoint(new Vector2(0,0));
        }

        if(Input.isKeyDown(Keys.SPACE)){
            body.addForce(Vector2.getDirection(angle).multiply(5/2));

        }
        if(Input.isKeyDown(Keys.SHIFT)){
            body.addForce(Vector2.getDirection(angle).multiply(-5/2));
        }
        JavaGameEngine.getSelectedScene().getCamera().setPosition(getFirstParent().getPosition().multiply(-1).add(JavaGameEngine.getWindowSize().divide(2)));

    }
}
