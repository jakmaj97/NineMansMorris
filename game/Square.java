package jakubmajchrzak.game;

import jakubmajchrzak.gameEnums.FieldState;

import java.util.ArrayList;
import java.util.List;

public class Square {

    private static final int SQUARE_FIELDS_AMOUNT = 4;

    private List<Field> cornerFields;
    private List<Field> centerFields;

    public Square() {
        cornerFields = new ArrayList<>(SQUARE_FIELDS_AMOUNT);
        centerFields = new ArrayList<>(SQUARE_FIELDS_AMOUNT);
    }

    public Square(Square otherSquare) {
        cornerFields = new ArrayList<>(SQUARE_FIELDS_AMOUNT);
        centerFields = new ArrayList<>(SQUARE_FIELDS_AMOUNT);

        for(int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            cornerFields.add(new Field(otherSquare.cornerFields.get(i)));
            centerFields.add(new Field(otherSquare.centerFields.get(i)));
        }
    }

    public List<Field> getCornerFields() {
        return cornerFields;
    }

    public List<Field> getCenterFields() {
        return centerFields;
    }

    public List<Field> getAllFields() {
        List<Field> allFieldsList = new ArrayList<>();

        for(int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            allFieldsList.add(cornerFields.get(i));
            allFieldsList.add(centerFields.get(i));
        }

        return allFieldsList;
    }

    public List<Field> getPlayerFields() {
        List<Field> allFieldsList = new ArrayList<>();

        for(int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            if(cornerFields.get(i).getFieldState() == FieldState.PLAYER)
                allFieldsList.add(cornerFields.get(i));
            if(centerFields.get(i).getFieldState() == FieldState.PLAYER)
                allFieldsList.add(centerFields.get(i));
        }

        return allFieldsList;
    }

    public List<Field> getComputerFields() {
        List<Field> allFieldsList = new ArrayList<>();

        for(int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            if(cornerFields.get(i).getFieldState() == FieldState.COMPUTER)
                allFieldsList.add(cornerFields.get(i));
            if(centerFields.get(i).getFieldState() == FieldState.COMPUTER)
                allFieldsList.add(centerFields.get(i));
        }

        return allFieldsList;
    }

    public List<Field> getAvailableFields() {
        List<Field> allFieldsList = new ArrayList<>();

        for(int i = 0; i < SQUARE_FIELDS_AMOUNT; i++) {
            if(cornerFields.get(i).getFieldState() == FieldState.AVAILABLE)
                allFieldsList.add(cornerFields.get(i));
            if(centerFields.get(i).getFieldState() == FieldState.AVAILABLE)
                allFieldsList.add(centerFields.get(i));
        }

        return allFieldsList;
    }

    public void setCornerFields(List<Field> cornerFields) {
        this.cornerFields = cornerFields;
    }

    public void setCenterFields(List<Field> centerFields) {
        this.centerFields = centerFields;
    }

    public void addCornerField(Field cornerField) {
        cornerFields.add(cornerField);
    }

    public void addCenterField(Field centerField) {
        centerFields.add(centerField);
    }
}
