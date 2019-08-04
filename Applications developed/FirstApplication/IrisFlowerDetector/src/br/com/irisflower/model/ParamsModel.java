package br.com.irisflower.model;

public class ParamsModel {

    private double _sepalLength;
    private double _sepalWidth;

    private double _petalLength;
    private double _petalWidth;

    public ParamsModel() {
        _sepalLength = 0.0;
        _sepalWidth = 0.0;
        _petalLength = 0.0;
        _petalWidth = 0.0;
    }

    public double getSepalLength() {
        return _sepalLength;
    }
    public void setSepalLength(double value) {
        _sepalLength = value;
    }

    public double getSepalWidth() {
        return _sepalWidth;
    }
    public void setSepalWidth(double value) {
        _sepalWidth = value;
    }

    public double getPetalLength() {
        return _petalLength;
    }
    public void setPetalLength(double value) {
        _petalLength = value;
    }

    public double getPetalWidth() {
        return _petalWidth;
    }
    public void setPetalWidth(double value) {
        _petalWidth = value;
    }
}
