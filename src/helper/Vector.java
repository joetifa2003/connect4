package helper;

import java.awt.event.MouseEvent;

public class Vector {
    private final double x;
    private final double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector(MouseEvent e) {
        this(e.getX(), e.getY());
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector add(double x, double y) {
        return new Vector(this.x + x, this.y + y);
    }

    public Vector add(double t) {
        return new Vector(this.x + t, this.y + t);
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector mul(double factor) {
        return new Vector(this.x * factor, this.y * factor);
    }

    public Vector mul(double x, double y) {
        return new Vector(this.x * x, this.y * y);
    }

    public Vector norm() {
        double len = this.len();
        if (len == 0) {
            return this;
        }

        return new Vector(this.x / len, this.y / len);
    }

    public Vector setX(double x) {
        return new Vector(x, this.y);
    }

    public Vector setY(double y) {
        return new Vector(this.x, y);
    }

    public Vector dirTo(Vector other) {
        return new Vector(other.x - this.x, other.y - this.y);
    }

    public double len() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }
}

