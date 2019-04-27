package jakubmajchrzak.game;

import jakubmajchrzak.gameEnums.FieldIndex;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Square outerSquare;
    private Square centralSquare;
    private Square innerSquare;
    private List<Mill> playerMillsBank;
    private List<Mill> cpuMillsBank;

    public Board() {
        outerSquare = new Square();
        centralSquare = new Square();
        innerSquare = new Square();
        playerMillsBank = new ArrayList<>();
        cpuMillsBank = new ArrayList<>();
    }

    public Board(Board otherBoard) {
        this.outerSquare = new Square(otherBoard.outerSquare);
        this.centralSquare = new Square(otherBoard.centralSquare);
        this.innerSquare = new Square(otherBoard.innerSquare);
        this.playerMillsBank = new ArrayList<>(otherBoard.playerMillsBank);
        this.cpuMillsBank = new ArrayList<>(otherBoard.cpuMillsBank);
    }

    public void disableAllFields() {
        List<Field> allFieldsList = getListOfAllFieldsFromAllSquares();
        for(Field field : allFieldsList)
            field.disableField();
    }

    public void enableAllFields() {
        List<Field> allFieldsList = getListOfAllFieldsFromAllSquares();
        for(Field field : allFieldsList)
            field.enableField();
    }

    public boolean catchUserMill() {
        for(Mill mill : playerMillsBank) {
            if(mill.isUserMillSatisfied()) {
                playerMillsBank.remove(mill);
                return true;
            }
        }
        return false;
    }

    public boolean catchCpuMill() {
        for(Mill mill : cpuMillsBank) {
            if(mill.isCpuMillSatisfied()) {
                cpuMillsBank.remove(mill);
                return true;
            }
        }
        return false;
    }

    public Square getOuterSquare() {
        return outerSquare;
    }

    public Square getCentralSquare() {
        return centralSquare;
    }

    public Square getInnerSquare() {
        return innerSquare;
    }

    public List<Square> getListOfAllSquares() {
        List<Square> listOfAllSquares = new ArrayList<>();

        listOfAllSquares.add(outerSquare);
        listOfAllSquares.add(centralSquare);
        listOfAllSquares.add(innerSquare);

        return listOfAllSquares;
    }

    public List<Field> getListOfAllFieldsFromAllSquares() {
        List<Square> listOfAllSquares = new ArrayList<>();
        List<Field> listOfAllFieldsFromAllSquares = new ArrayList<>();

        listOfAllSquares.add(outerSquare);
        listOfAllSquares.add(centralSquare);
        listOfAllSquares.add(innerSquare);

        for(Square square : listOfAllSquares)
            listOfAllFieldsFromAllSquares.addAll(square.getAllFields());

        return listOfAllFieldsFromAllSquares;
    }

    public List<Field> getListOfAvailableFields() {
        List<Square> listOfAllSquares = new ArrayList<>();
        List<Field> listOfAllFieldsFromAllSquares = new ArrayList<>();

        listOfAllSquares.add(outerSquare);
        listOfAllSquares.add(centralSquare);
        listOfAllSquares.add(innerSquare);

        for(Square square : listOfAllSquares)
            listOfAllFieldsFromAllSquares.addAll(square.getAvailableFields());

        return listOfAllFieldsFromAllSquares;
    }

    public List<Field> getListOfAllPlayerFields() {
        List<Square> listOfAllSquares = new ArrayList<>();
        List<Field> listOfAllFieldsFromAllSquares = new ArrayList<>();

        listOfAllSquares.add(outerSquare);
        listOfAllSquares.add(centralSquare);
        listOfAllSquares.add(innerSquare);

        for(Square square : listOfAllSquares)
            listOfAllFieldsFromAllSquares.addAll(square.getPlayerFields());

        return listOfAllFieldsFromAllSquares;
    }

    public List<FieldIndex> getListOfFieldIndexes() {
        List<FieldIndex> fieldIndices = new ArrayList<>();
        List<Field> allFields = getListOfAllFieldsFromAllSquares();

        for(Field field : allFields)
            fieldIndices.add(field.getFieldIndex());

        return fieldIndices;
    }

    public List<Field> getListOfAllComputerFields() {
        List<Square> listOfAllSquares = new ArrayList<>();
        List<Field> listOfAllFieldsFromAllSquares = new ArrayList<>();

        listOfAllSquares.add(outerSquare);
        listOfAllSquares.add(centralSquare);
        listOfAllSquares.add(innerSquare);

        for(Square square : listOfAllSquares)
            listOfAllFieldsFromAllSquares.addAll(square.getComputerFields());

        return listOfAllFieldsFromAllSquares;
    }

    public void setOuterSquare(Square outerSquare) {
        this.outerSquare = outerSquare;
    }

    public void setCentralSquare(Square centralSquare) {
        this.centralSquare = centralSquare;
    }

    public void setInnerSquare(Square innerSquare) {
        this.innerSquare = innerSquare;
    }

    public Field getFieldByIndex(FieldIndex fieldIndex) {
        List<Field> fieldList = getListOfAllFieldsFromAllSquares();
        for(Field field : fieldList)
            if(field.getFieldIndex() == fieldIndex)
                return field;
        return null;
    }

    public void addPlayerMillsBank() {
        playerMillsBank = getPlayerMillsBank();
    }

    public void addCpuMillsBank() {
        cpuMillsBank = getCpuMillsBank();
    }

    private List<Mill> getPlayerMillsBank() {
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A7), getFieldByIndex(FieldIndex.D7), getFieldByIndex(FieldIndex.G7)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A7), getFieldByIndex(FieldIndex.A4), getFieldByIndex(FieldIndex.A1)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.G7), getFieldByIndex(FieldIndex.G4), getFieldByIndex(FieldIndex.G1)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A1), getFieldByIndex(FieldIndex.D1), getFieldByIndex(FieldIndex.G1)));

        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B6), getFieldByIndex(FieldIndex.D6), getFieldByIndex(FieldIndex.F6)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B6), getFieldByIndex(FieldIndex.B4), getFieldByIndex(FieldIndex.B2)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B2), getFieldByIndex(FieldIndex.D2), getFieldByIndex(FieldIndex.F2)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.F6), getFieldByIndex(FieldIndex.F4), getFieldByIndex(FieldIndex.F2)));

        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C5), getFieldByIndex(FieldIndex.D5), getFieldByIndex(FieldIndex.E5)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C5), getFieldByIndex(FieldIndex.C4), getFieldByIndex(FieldIndex.C3)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C3), getFieldByIndex(FieldIndex.D3), getFieldByIndex(FieldIndex.E3)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.E3), getFieldByIndex(FieldIndex.E4), getFieldByIndex(FieldIndex.E5)));

        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A4), getFieldByIndex(FieldIndex.B4), getFieldByIndex(FieldIndex.C4)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.E4), getFieldByIndex(FieldIndex.F4), getFieldByIndex(FieldIndex.G4)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.D1), getFieldByIndex(FieldIndex.D2), getFieldByIndex(FieldIndex.D3)));
        playerMillsBank.add(new Mill(getFieldByIndex(FieldIndex.D5), getFieldByIndex(FieldIndex.D6), getFieldByIndex(FieldIndex.D7)));

        return playerMillsBank;
    }

    private List<Mill> getCpuMillsBank() {
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A7), getFieldByIndex(FieldIndex.D7), getFieldByIndex(FieldIndex.G7)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A7), getFieldByIndex(FieldIndex.A4), getFieldByIndex(FieldIndex.A1)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.G7), getFieldByIndex(FieldIndex.G4), getFieldByIndex(FieldIndex.G1)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A1), getFieldByIndex(FieldIndex.D1), getFieldByIndex(FieldIndex.G1)));

        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B6), getFieldByIndex(FieldIndex.D6), getFieldByIndex(FieldIndex.F6)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B6), getFieldByIndex(FieldIndex.B4), getFieldByIndex(FieldIndex.B2)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.B2), getFieldByIndex(FieldIndex.D2), getFieldByIndex(FieldIndex.F2)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.F6), getFieldByIndex(FieldIndex.F4), getFieldByIndex(FieldIndex.F2)));

        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C5), getFieldByIndex(FieldIndex.D5), getFieldByIndex(FieldIndex.E5)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C5), getFieldByIndex(FieldIndex.C4), getFieldByIndex(FieldIndex.C3)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.C3), getFieldByIndex(FieldIndex.D3), getFieldByIndex(FieldIndex.E3)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.E3), getFieldByIndex(FieldIndex.E4), getFieldByIndex(FieldIndex.E5)));

        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.A4), getFieldByIndex(FieldIndex.B4), getFieldByIndex(FieldIndex.C4)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.E4), getFieldByIndex(FieldIndex.F4), getFieldByIndex(FieldIndex.G4)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.D1), getFieldByIndex(FieldIndex.D2), getFieldByIndex(FieldIndex.D3)));
        cpuMillsBank.add(new Mill(getFieldByIndex(FieldIndex.D5), getFieldByIndex(FieldIndex.D6), getFieldByIndex(FieldIndex.D7)));

        return cpuMillsBank;
    }
}
