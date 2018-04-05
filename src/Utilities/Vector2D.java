package Utilities;
/**
 * Created by kr16264 on 23/01/2018.
 */
// mutable 2D vectors
public final class Vector2D {
    public double x, y;

    // constructor for zero vector
    public Vector2D() {
        x = 0.0;
        y = 0.0;

    }

    // constructor for vector with given coordinates
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // constructor that copies the argument vector
    public Vector2D(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
    }

    // set coordinates
    public Vector2D set(double x, double y) {
        this.x = x;
        this.y = y;

        return this;
    }

    // set coordinates based on argument vector
    public Vector2D set(Vector2D v) {
        this.x = v.x;
        this.y = v.y;
        return this;

    }

    // compare for equality (note Object type argument)
    public boolean equals(Object o) {
        if (this.getClass() == o.getClass()) {
            if (this.toString().equals(o.toString())) {
                return true;
            }
        }
        return false;
    }

    // String for displaying vector as text
    public String toString() {
        return "Vector2D (X= " + x + " Y= " + y+ ")";
    }

    //  magnitude (= "length") of this vector
    public double mag() {
        return Math.sqrt(x*x + y*y);
    }

    // angle between vector and horizontal axis in radians in range [-PI,PI]
// can be calculated using Math.atan2
    public double angle() {
        return Math.atan2(this.y, this.x);
    }

    // angle between this vector and another vector in range [-PI,PI]
    public double angle(Vector2D other) {
        return Math.atan2(this.y - other.y ,this.x - other.x);
    }

    // add argument vector
    public Vector2D add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
        return this;
    }

    // add values to coordinates
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    // weighted add - surprisingly useful
    public Vector2D addScaled(Vector2D v, double fac) {
        this.x += v.x *fac;
        this.y += v.y *fac;
        return this;
    }

    // subtract argument vector
    public Vector2D subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }

    // subtract values from coordinates
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    // multiply with factor
    public Vector2D mult(double fac) {
        this.x = this.x * fac;
        this.y = this.y * fac;
        return this;
    }

    // rotate by angle given in radians
    public Vector2D rotate(double angle) {
        double temp = this.x;
        this.x = this.x * Math.cos(angle) - this.y * Math.sin(angle);
        this.y = temp * Math.sin(angle) + this.y * Math.cos(angle);
        return this;
    }

    // "dot product" ("scalar product") with argument vector
    public double dot(Vector2D v) {
        return this.x * v.x + this.y * v.y;
    }

    // distance to argument vector
    public double dist(Vector2D v) {
        return Math.hypot(this.x - v.x, this.y - v.y);

    }

    // normalise vector so that magnitude becomes 1
    public Vector2D normalise() {
        double length = this.mag();
        if (length !=0){
            this.x = this.x/length;
            this.y = this.y/length;
        }
        return this;
    }

    // wrap-around operation, assumes w> 0 and h>0
    // remember to manage negative values of the coordinates
    public Vector2D wrap(double w, double h) {
        this.x = (this.x + w) % w;
        this.y = (this.y + h) % h;
        return this;

    }

    // construct vector with given polar coordinates
    public static Vector2D polar(double angle, double mag) {
        Vector2D v = new Vector2D(mag * Math.cos(angle),mag * Math.sin(angle));
        return v;
    }

    public Vector2D reset(){
        x = 0;
        y = 0;
        return this;
    }

    public Vector2D flip(){
        x = -x;
        y = -y;
        return this;
    }

}