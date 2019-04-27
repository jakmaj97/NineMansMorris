package jakubmajchrzak.game;

import jakubmajchrzak.gameEnums.FieldIndex;
import jakubmajchrzak.gameEnums.FieldState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Field {

    private static final Image userPawnImage = new Image("/images/user_pawn.png");
    private static final Image cpuPawnImage = new Image("/images/cpu_pawn.png");
    private static final Image emptyFieldImage = new Image("/images/empty_field.png");
    private static final Image availableFieldImage = new Image("/images/available_field.png");

    private FieldIndex fieldIndex;
    private ImageView fieldImage;
    private FieldState fieldState;

    public Field(FieldIndex fieldIndex, ImageView fieldImage, FieldState fieldState) {
        this.fieldIndex = fieldIndex;
        this.fieldImage = fieldImage;
        this.fieldState = fieldState;
        setImageByFieldState(fieldState);
    }

    public Field(FieldIndex fieldIndex, ImageView fieldImage) {
        this.fieldIndex = fieldIndex;
        this.fieldImage = fieldImage;
        this.fieldState = FieldState.UNCHECKED;
        this.fieldImage.setImage(emptyFieldImage);
    }

    public Field(Field otherField) {
        this.fieldIndex = otherField.fieldIndex;
        this.fieldImage = otherField.fieldImage;
        this.fieldState = otherField.fieldState;
    }

    public void disableField() {
        this.fieldImage.setDisable(true);
    }

    public void enableField() {
        this.getFieldImage().setDisable(false);
    }

    public FieldIndex getFieldIndex() {
        return fieldIndex;
    }

    public ImageView getFieldImage() {
        return fieldImage;
    }

    public FieldState getFieldState() {
        return fieldState;
    }

    public void setFieldIndex(FieldIndex fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public void setFieldImage(ImageView fieldImage) {
        this.fieldImage = fieldImage;
    }

    public void setFieldState(FieldState fieldState) {
        this.fieldState = fieldState;
        setImageByFieldState(fieldState);
    }

    private void setImageByFieldState(FieldState fieldState) {
        switch (fieldState) {
            case PLAYER: {
                fieldImage.setImage(userPawnImage);
                break;
            }
            case COMPUTER: {
                fieldImage.setImage(cpuPawnImage);
                break;
            }
            case UNCHECKED: {
                fieldImage.setImage(emptyFieldImage);
                break;
            }
            case AVAILABLE: {
                fieldImage.setImage(availableFieldImage);
                break;
            }
        }
    }

}
