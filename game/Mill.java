package jakubmajchrzak.game;

import jakubmajchrzak.gameEnums.FieldState;

public class Mill {

    private Field firstField;
    private Field secondField;
    private Field thirdField;

    public Mill(Field firstField, Field secondField, Field thirdField) {
        this.firstField = firstField;
        this.secondField = secondField;
        this.thirdField = thirdField;
    }

    public boolean isUserMillSatisfied() {
        return firstField.getFieldState() == FieldState.PLAYER
                && secondField.getFieldState() == FieldState.PLAYER
                && thirdField.getFieldState() == FieldState.PLAYER;
    }

    public boolean isCpuMillSatisfied() {
        return firstField.getFieldState() == FieldState.COMPUTER
                && secondField.getFieldState() == FieldState.COMPUTER
                && thirdField.getFieldState() == FieldState.COMPUTER;
    }
}
