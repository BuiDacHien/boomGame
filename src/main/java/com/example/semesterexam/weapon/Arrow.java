package com.example.semesterexam.weapon;

import com.example.semesterexam.core.Bullet;
import com.example.semesterexam.core.Character;
import com.example.semesterexam.core.Monster;
import com.example.semesterexam.core.Subject;
import com.example.semesterexam.lanscape.ConcreteWall;
import com.example.semesterexam.manage.GameScreen;
import javafx.animation.AnimationTimer;

import java.io.IOException;

public class Arrow extends Bullet {

    public Arrow(GameScreen gameScreen, Character owner) throws IOException {
        super(gameScreen, owner);
    }

    @Override
    public void setRealitySize() {
        if (dx != 0) {
            heightReality = getFitHeight() * 0.18d;
        } else {
            widthReality = getFitWidth() * 0.18d;
        }
    }



    @Override
    public void effect() {
        Boom boom = null;
        try {
            boom = new Boom(this, gameScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert boom != null;

        if (subject != null) {
            if (!(subject instanceof ConcreteWall)) {
                boom.setX(subject.getX());
                boom.setY(subject.getY());
            }
        } else {
            boom.setX(this.getX());
            boom.setY(this.getY());
        }
        boom.setDamage(owner.getBaseDamage() * owner.getIncreaseDamage());
        boom.setAllRange(3d);
        if (owner instanceof Monster) {
            boom.setAllRange(1.5d);
        }
        adjustRange(boom);
        gameScreen.getMap().getChildren().add(boom);
        boom.countdown(10);
    }



    public void adjustRange(Boom boom) {
        if (dx > 0) {
            boom.rangeLeft = 1d;
        } else if (dx < 0) {
            boom.rangeRight = 1d;
        } else if (dy > 0) {
            boom.rangeTop = 1d;
        } else {
            boom.rangeDown = 1d;
        }
    }


    @Override
    public void setDefaultActions() {
        addActions("ArrowLeft", gameScreen.getAction("ArrowPack:ArrowLeft"));
        addActions("ArrowRight", gameScreen.getAction("ArrowPack:ArrowRight"));
        addActions("ArrowUp", gameScreen.getAction("ArrowPack:ArrowUp"));
        addActions("ArrowDown", gameScreen.getAction("ArrowPack:ArrowDown"));
    }

    @Override
    public String getName() {
        return "Arrow";
    }


}
