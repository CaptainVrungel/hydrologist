package ru.hydrologist.drawing;


import ru.hydrologist.coefficients.HasenGraphCoefficient;
import ru.hydrologist.utils.DataConverter;
import ru.hydrologist.utils.Interpolator;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Arrays;
import java.util.Map;

//Метод подписания точек тоже надо переделывать
public class EnsuranceCurve extends Graph{
    private boolean AxisXfull = true;               //Параметр отображения полной сетки по х (от 0,01 до 99,99) или укороченной (от 0,1 до 99,9)

    public EnsuranceCurve(DrawableDataSet dataSet){
        super(dataSet);
        this.xTransformFactor = ((double) getAxisXLength())/getAxisXDiapason();

    }

    //рисуем координатную сетку на оси Х
    protected void drawGridX(){
        Graphics2D axisGraphics = getGraphicsObject(getAxisColor(), getAxisLabelTextSize());
        Graphics2D additionalAxisGraphics = getGraphicsObject(getAdditionalAxisColor(), "additional");

        //Рисуем деления на шкале x
        for (Map.Entry<Double, Double> entry : getAxisXValues().entrySet()) {
            double currX = entry.getValue()*xTransformFactor; //Текущее значение на оси Х

            additionalAxisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + currX, getAxisYCoordinate(), getAxisXCoordinate() + currX, getAxisYCoordinate()-getYAxisLength())); //рисуем промежуточные линии на x (Кривая Хазена)

            if( Arrays.asList(HasenGraphCoefficient.getInstance().getPrintedValues()).contains(entry.getKey())) { // Проверяем, если значение Р встречается в массиве со значениями, которые мы хотим подписывать
                axisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + currX, getAxisYCoordinate(), getAxisXCoordinate() + currX, getAxisYCoordinate()+getDashSize()/2)); //рисуем риски на x (Кривая Хазена)
                axisGraphics.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 1, new float[]{6f, 6f}, 0f));
                axisGraphics.draw(new Line2D.Double(getAxisXCoordinate() + currX, getAxisYCoordinate(), getAxisXCoordinate() + currX, getAxisYCoordinate()-getYAxisLength()));       // Подписываем риски на x
            }

        }
    }

    protected void  drawGridXLabels(){
        Graphics2D axisGraphics = getGraphicsObject(getAxisColor(), getAxisLabelTextSize());

        for (Map.Entry<Double, Double> entry : getAxisXValues().entrySet()) {
            double currX = entry.getValue()*xTransformFactor; //Текущее значение на оси Х
            int valueTextLength = Double.toString(entry.getValue()).length();

            if( Arrays.asList(HasenGraphCoefficient.getInstance().getPrintedValues()).contains(entry.getKey())) { // Проверяем, если значение Р встречается в массиве со значениями, которые мы хотим подписывать
                axisGraphics.drawString(entry.getKey().toString(), (int) ((double)getAxisXCoordinate()+currX - (double)valueTextLength*xTransformFactor/2.5), (int) ((double)getAxisYCoordinate()+((double)getAxisTextSize())*1.3)); // Подписываем риски на x// Подписываем риски на x
            }
        }
    }

    //Наносим на график значения
    protected void drawData(){
        Graphics2D pointsGraphics = (Graphics2D) image.getGraphics();

        for(DrawableData data: dataSet.getDrawingDataSet()) {
            pointsGraphics.setColor(data.getPointsColor());

            for (GraphPoint point : data.getPoints()) {
                //System.out.println("point.getXCoordinate(): " + point.getXCoordinate()*100 + " xBetween[0]: " + xBetween[0] + " xBetween[1]: " + xBetween[1] + " currPMin: " + currPMin + " currPMax: " + currPMax + " CurrDist: " + CurrDist + " pixelX: " + pixelX + " pixelY: " + pixelY);

                if (data.fillPoints()) {
                    pointsGraphics.fill(new Ellipse2D.Double(getTransformedXCoordinate(point) - getPointSize() / 2, getTransformedYCoordinate(point) - getPointSize() / 2, getPointSize(), getPointSize()));
                } else {
                    pointsGraphics.draw(new Ellipse2D.Double(getTransformedXCoordinate(point) - getPointSize() / 2, getTransformedYCoordinate(point) - -getPointSize() / 2, getPointSize(), getPointSize()));
                }
            }
        }
    }

    //Подписываем данные на графике
    protected void inscriptData(){
        for(DrawableData data: dataSet.getDrawingDataSet()) {
            Graphics2D pointsGraphics = getGraphicsObject(data.getPointsColor(), getPointsTextSize());

            if (data.DrawInscription()) {
                for (GraphPoint point : data.getPoints()) {
                    pointsGraphics.drawString(DataConverter.smartHydrologicalRoundToString(point.getYCoordinate()).toString(), getTransformedXCoordinate(point), getTransformedYCoordinate(point) - (int) (getPointSize() / 1.5));
                }
            }
        }
    }

    protected int getTransformedXCoordinate(GraphPoint point){
        //находим между какими значениями Вероятности (P) попал наш икс
        Double[] xBetween = Interpolator.findBetweenWhich(point.getXCoordinate(), getAxisXValues());
        //Интерполируем
        double PMinimum = getAxisXValues().get(xBetween[0]);
        double PMaximum = getAxisXValues().get(xBetween[1]);
        double Distance = Interpolator.interpolation(point.getXCoordinate(), xBetween[0], xBetween[1],PMinimum, PMaximum);

        return (int) (Distance*this.xTransformFactor + getAxisXCoordinate()); //Координата x точки;
    }

    //Возвращаем ординаты кривой Хазена, чтобы отложить их по оси Х
    public Map<Double, Double> getAxisXValues(){
        if(isAxisXfull() == true){
            return HasenGraphCoefficient.getInstance().getLongCoefficients();
        }else{
            return HasenGraphCoefficient.getInstance().getShortCoefficients();
        }
    }

    //Полная или не полная шкала по оси X
    public boolean isAxisXfull() {
        return AxisXfull;
    }

    public Double getAxisXDiapason(){
        return HasenGraphCoefficient.getInstance().getTotalDistanceLength();
    }
}
