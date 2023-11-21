package com.rat6.game.game_objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/**
 * Rectangle position represents left lower corner, while in Circle position is centre.
 * I always used Circles because of the fucking misalignment of rendering and real coordinates.
 * */
public abstract class GameObject {
    public enum Shape {
        CIRCLE,
        RECTANGLE
    }
    public int id;

    public Shape shape;
    public Circle circle;
    public Rectangle rectangle;

    public ObjectState state = ObjectState.STANDING;
    public ObjectDirection direction = ObjectDirection.UNDEFINED;
    public float speed;
    public Vector2 velocity;

    public GameObject(){
        id = (int)(Math.random() * 1000000);
        state = ObjectState.STANDING;
        direction = ObjectDirection.UNDEFINED;
    }

    public GameObject rectangle(float x, float y, float width, float height){
//        System.out.println(id);

        shape = Shape.RECTANGLE;
        rectangle = new Rectangle();
        rectangle.setX(x - rectangle.width);
        rectangle.setY(y - rectangle.height);
        rectangle.setWidth(width);
        rectangle.setHeight(height);
        return this;
    }

    public GameObject circle(float x, float y, float radius){
        shape = Shape.CIRCLE;
        circle = new Circle();
        circle.setX(x);
        circle.setY(y);
        circle.setRadius(radius);
        return this;
    }
    public GameObject setPosition(float x, float y){
        if(shape == Shape.CIRCLE){
            circle.setX(x);
            circle.setY(y);
        }
        else {
            rectangle.setX(x - rectangle.width);
            rectangle.setY(y - rectangle.height);
        }
        return this;
    }
    public GameObject setRadius(float radius){
        circle.setRadius(radius);
        return this;
    }

    public void move(ObjectDirection direction){
        if(state == ObjectState.DEAD){
            return;
        }
        if(state == ObjectState.SHOOTING) {
            return;
        }
        state = ObjectState.MOVING;
        this.direction = direction;
    }

    public void stop(){
        if(state == ObjectState.DEAD){
            return;
        }
        if(state == ObjectState.SHOOTING) {
            return;
        }
        state = ObjectState.STANDING;
    }

    public boolean overlap(GameObject gameObject) {
        if(this.id == gameObject.id){
            return false;
        }

        // Проверяем, если оба объекта - круги
        if (this.shape == Shape.CIRCLE && gameObject.shape == Shape.CIRCLE) {
            return this.circle.overlaps(gameObject.circle);
        }
        // Проверяем, если оба объекта - прямоугольники
        else if (this.shape == Shape.RECTANGLE && gameObject.shape == Shape.RECTANGLE) {
            return this.rectangle.overlaps(gameObject.rectangle);
        }
        // Проверяем, если один объект - круг, а другой - прямоугольник
        else if (this.shape == Shape.CIRCLE && gameObject.shape == Shape.RECTANGLE) {
            return Intersector.overlaps(this.circle, gameObject.rectangle);
        }
        else if (this.shape == Shape.RECTANGLE && gameObject.shape == Shape.CIRCLE) {
            return Intersector.overlaps(gameObject.circle, this.rectangle);
        }
        // Если формы не определены или не поддерживаются
        return false;
    }

    public boolean overlap(Circle circleObject) {
        if (shape == Shape.CIRCLE) {
            return this.circle.overlaps(circleObject);
        }
        else if (shape == Shape.RECTANGLE) {
            return Intersector.overlaps(circleObject, rectangle);
        }
        // Если формы не определены или не поддерживаются
        return false;
    }

    public boolean overlap(Rectangle rectangleObject) {
        // Проверяем, если оба объекта - прямоугольники
        if (shape == Shape.RECTANGLE) {
            return rectangle.overlaps(rectangleObject);
        }
        // Проверяем, если один объект - круг, а другой - прямоугольник
        else if (shape == Shape.CIRCLE) {
            return Intersector.overlaps(circle, rectangleObject);
        }
        // Если формы не определены или не поддерживаются
        return false;
    }

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch);
    // Animation or
}
