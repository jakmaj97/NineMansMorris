package jakubmajchrzak.userInterfaceClasses;

import jakubmajchrzak.game.*;
import jakubmajchrzak.gameEnums.FieldIndex;
import jakubmajchrzak.gameEnums.FieldState;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleGround extends Application {

    private static final Image redTrafficImage = new Image("/images/red_traffic.png");
    private static final Image greenTrafficImage = new Image("/images/green_traffic.png");

    private static final int BATTLEGROUND_WIDTH = 1000;
    private static final int BATTLEGROUND_HEIGHT = 800;
    private static final int WINNING_MESSAGE_WIDTH = 400;
    private static final int WINNING_MESSAGE_HEIGHT = 200;

    private static final String GAME_TITLE = "Gra logiczna młynek";
    private static final String PLAYER_WINNER = "gracz";
    private static final String CPU_WINNER = "komputer";

    @FXML private Label userPawnsToSetLabel;
    @FXML private Label cpuPawnsToSetLabel;
    @FXML private Label userPawnsLeftLabel;
    @FXML private Label cpuPawnsLeftLabel;
    @FXML private Label userMillsLabel;
    @FXML private Label cpuMillsLabel;
    @FXML private Label movesAmountLabel;

    @FXML private RadioButton minMaxRadioButton;
    @FXML private RadioButton alphaBetaRadioButton;

    @FXML private RadioButton heutistic1RadioButton;
    @FXML private RadioButton heutistic2RadioButton;
    @FXML private RadioButton heutistic3RadioButton;

    @FXML private Button startButton;
    @FXML private Button previousBoardButton;
    @FXML private Button nextBoardButton;

    @FXML private ImageView roadLightsImageView;

    private Board board;
    private List<List<Field>> colouredFields;
    private int boardNumber;

    private List<Integer> userPawnsToSetList;
    private List<Integer> cpuPawnsToSetList;

    private List<Integer> userMillsList;
    private List<Integer> cpuMillsList;

    private List<Integer> userPawnsLeftList;
    private List<Integer> cpuPawnsLeftList;

    private int userPawnsToSet;
    private int cpuPawnsToSet;

    private int userMills;
    private int cpuMills;

    private int userPawnsLeft;
    private int cpuPawnsLeft;

    private int movesAmount;

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Parent battleGroundWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/battle_ground.fxml"));
        final Scene battleGroundScene = new Scene(battleGroundWindow, BATTLEGROUND_WIDTH, BATTLEGROUND_HEIGHT);

        initLabels(battleGroundScene);
        initButtons(battleGroundScene);
        initBoard(battleGroundScene);

        setStartButton();
        setPreviousBoardButton();
        setNextBoardButton();

        setOnClickToAllFieldsForFirstRound();

        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setScene(battleGroundScene);
        primaryStage.show();
    }

    private void initLabels(Scene scene) {
        userPawnsToSetLabel = (Label) scene.lookup("#userPawnsToSetLabel");
        cpuPawnsToSetLabel = (Label) scene.lookup("#cpuPawnsToSetLabel");

        userPawnsLeftLabel = (Label) scene.lookup("#userPawnsLeftLabel");
        cpuPawnsLeftLabel = (Label) scene.lookup("#cpuPawnsLeftLabel");

        userMillsLabel = (Label) scene.lookup("#userMillsLabel");
        cpuMillsLabel = (Label) scene.lookup("#cpuMillsLabel");

        movesAmountLabel = (Label) scene.lookup("#movesAmountLabel");

        roadLightsImageView = (ImageView) scene.lookup("#roadLightImageView");
    }

    private void initButtons(Scene scene) {
        minMaxRadioButton = (RadioButton) scene.lookup("#minMaxRadioButton");
        alphaBetaRadioButton = (RadioButton) scene.lookup("#alphaBeraRadioButton");

        heutistic1RadioButton = (RadioButton) scene.lookup("#heuristic1RadioButton");
        heutistic2RadioButton = (RadioButton) scene.lookup("#heuristic2RadioButton");
        heutistic3RadioButton = (RadioButton) scene.lookup("#heuristic3RadioButton");

        startButton = (Button) scene.lookup("#startButton");

        previousBoardButton = (Button) scene.lookup("#previousBoardButton");
        nextBoardButton = (Button) scene.lookup("#nextBoardButton");
    }

    private void initBoard(Scene scene) {
        board = new Board();
        colouredFields = new ArrayList<>();
        boardNumber = 0;

        userPawnsToSetList = new ArrayList<>();
        cpuPawnsToSetList = new ArrayList<>();

        userMillsList = new ArrayList<>();
        cpuMillsList = new ArrayList<>();

        userPawnsLeftList = new ArrayList<>();
        cpuPawnsLeftList = new ArrayList<>();

        Square outerSquare = board.getOuterSquare();
        Square centralSquare = board.getCentralSquare();
        Square innerSquare = board.getInnerSquare();

        outerSquare.addCornerField(new Field(FieldIndex.A7, (ImageView) scene.lookup("#A7")));
        outerSquare.addCornerField(new Field(FieldIndex.G7, (ImageView) scene.lookup("#G7")));
        outerSquare.addCornerField(new Field(FieldIndex.G1, (ImageView) scene.lookup("#G1")));
        outerSquare.addCornerField(new Field(FieldIndex.A1, (ImageView) scene.lookup("#A1")));

        outerSquare.addCenterField(new Field(FieldIndex.D7, (ImageView) scene.lookup("#D7")));
        outerSquare.addCenterField(new Field(FieldIndex.G4, (ImageView) scene.lookup("#G4")));
        outerSquare.addCenterField(new Field(FieldIndex.D1, (ImageView) scene.lookup("#D1")));
        outerSquare.addCenterField(new Field(FieldIndex.A4, (ImageView) scene.lookup("#A4")));


        centralSquare.addCornerField(new Field(FieldIndex.B6, (ImageView) scene.lookup("#B6")));
        centralSquare.addCornerField(new Field(FieldIndex.F6, (ImageView) scene.lookup("#F6")));
        centralSquare.addCornerField(new Field(FieldIndex.F2, (ImageView) scene.lookup("#F2")));
        centralSquare.addCornerField(new Field(FieldIndex.B2, (ImageView) scene.lookup("#B2")));

        centralSquare.addCenterField(new Field(FieldIndex.D6, (ImageView) scene.lookup("#D6")));
        centralSquare.addCenterField(new Field(FieldIndex.F4, (ImageView) scene.lookup("#F4")));
        centralSquare.addCenterField(new Field(FieldIndex.D2, (ImageView) scene.lookup("#D2")));
        centralSquare.addCenterField(new Field(FieldIndex.B4, (ImageView) scene.lookup("#B4")));


        innerSquare.addCornerField(new Field(FieldIndex.C5, (ImageView) scene.lookup("#C5")));
        innerSquare.addCornerField(new Field(FieldIndex.E5, (ImageView) scene.lookup("#E5")));
        innerSquare.addCornerField(new Field(FieldIndex.E3, (ImageView) scene.lookup("#E3")));
        innerSquare.addCornerField(new Field(FieldIndex.C3, (ImageView) scene.lookup("#C3")));

        innerSquare.addCenterField(new Field(FieldIndex.D5, (ImageView) scene.lookup("#D5")));
        innerSquare.addCenterField(new Field(FieldIndex.E4, (ImageView) scene.lookup("#E4")));
        innerSquare.addCenterField(new Field(FieldIndex.D3, (ImageView) scene.lookup("#D3")));
        innerSquare.addCenterField(new Field(FieldIndex.C4, (ImageView) scene.lookup("#C4")));

        board.addPlayerMillsBank();
        board.addCpuMillsBank();
        board.disableAllFields();
    }

    private void setStartButton() {
        startButton.setOnAction(event -> {
            board.enableAllFields();

            userPawnsToSet = 9;
            cpuPawnsToSet = 9;

            userMills = 0;
            cpuMills = 0;

            userPawnsLeft = 9;
            cpuPawnsLeft = 9;

            userPawnsToSetLabel.setText(String.valueOf(userPawnsToSet));
            cpuPawnsToSetLabel.setText(String.valueOf(cpuPawnsToSet));

            userPawnsLeftLabel.setText(String.valueOf(userPawnsLeft));
            cpuPawnsLeftLabel.setText(String.valueOf(cpuPawnsLeft));

            movesAmountLabel.setText(String.valueOf(movesAmount));

            roadLightsImageView.setImage(greenTrafficImage);

            colouredFields.add(getColouredFields());
            saveGameParametersIntoLists();
            boardNumber++;
        });
    }

    private void setPreviousBoardButton() {
        previousBoardButton.setOnAction(event -> {
            if(boardNumber > 1) {
                int boardNum = --boardNumber;
                List<Field> fieldList = board.getListOfAllFieldsFromAllSquares();
                for (Field field : fieldList)
                    field.setFieldState(FieldState.UNCHECKED);

                List<Field> targetFields = colouredFields.get(boardNum-1);

                for (Field field : targetFields)
                    board.getFieldByIndex(field.getFieldIndex()).setFieldState(field.getFieldState());

                setGameParameters(boardNum-1);


                board.disableAllFields();

            }
        });
    }

    private void setNextBoardButton() {
        nextBoardButton.setOnAction(event -> {
            if(boardNumber <= movesAmount) {
                int boardNum = boardNumber++;
                List<Field> fieldList = board.getListOfAllFieldsFromAllSquares();
                for (Field field : fieldList)
                    field.setFieldState(FieldState.UNCHECKED);


                List<Field> targetFields = colouredFields.get(boardNum);

                for (Field field : targetFields)
                    board.getFieldByIndex(field.getFieldIndex()).setFieldState(field.getFieldState());

                setGameParameters(boardNum);

                if(boardNum == movesAmount)
                    board.enableAllFields();
            }
        });
    }

    private List<Field> getColouredFields() {
        List<Field> fieldList = board.getListOfAllFieldsFromAllSquares();
        List<Field> colouredFields = new ArrayList<>();

        for(Field field : fieldList)
            if(field.getFieldState() == FieldState.PLAYER || field.getFieldState() == FieldState.COMPUTER)
                colouredFields.add(new Field(field));

        return colouredFields;
    }

    private void setGameParameters(int boardNum) {
        userPawnsToSet = userPawnsToSetList.get(boardNum);
        userPawnsToSetLabel.setText(String.valueOf(userPawnsToSet));
        cpuPawnsToSet = cpuPawnsToSetList.get(boardNum);
        cpuPawnsToSetLabel.setText(String.valueOf(cpuPawnsToSet));

        userMills = userMillsList.get(boardNum);
        userMillsLabel.setText(String.valueOf(userMills));
        cpuMills = cpuMillsList.get(boardNum);
        cpuMillsLabel.setText(String.valueOf(cpuMills));

        userPawnsLeft = userPawnsLeftList.get(boardNum);
        userPawnsLeftLabel.setText(String.valueOf(userPawnsLeft));
        cpuPawnsLeft = cpuPawnsLeftList.get(boardNum);
        cpuPawnsLeftLabel.setText(String.valueOf(cpuPawnsLeft));
    }

    private void saveGameParametersIntoLists() {
        userPawnsToSetList.add(userPawnsToSet);
        cpuPawnsToSetList.add(cpuPawnsToSet);

        userMillsList.add(userMills);
        cpuMillsList.add(cpuMills);

        userPawnsLeftList.add(userPawnsLeft);
        cpuPawnsLeftList.add(cpuPawnsLeft);
    }

    private void showWinningMessage(boolean playerWon) throws IOException {
        final Parent winningMessageWindow = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/winning_message.fxml"));
        final Scene winningMessageScene = new Scene(winningMessageWindow, WINNING_MESSAGE_WIDTH, WINNING_MESSAGE_HEIGHT);
        final Stage winningMessageStage = new Stage();

        Button okButton = (Button) winningMessageScene.lookup("#closeMessageButton");
        Label winnerLabel = (Label) winningMessageScene.lookup("#winnerLabel");

        okButton.setOnAction(event -> winningMessageStage.close());

        if(playerWon)
            winnerLabel.setText(winnerLabel.getText() + PLAYER_WINNER);
        else
            winnerLabel.setText(winnerLabel.getText() + CPU_WINNER);

        winningMessageStage.setScene(winningMessageScene);
        winningMessageStage.setTitle(GAME_TITLE);
        winningMessageStage.show();
    }

    /*--------------FIRST ROUND-------------*/

    private void setOnClickToAllFieldsForFirstRound() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();

        for(Field field : allFieldsList) {
            field.getFieldImage().setOnMouseClicked(event -> {
                if(field.getFieldState() == FieldState.UNCHECKED) {
                    movesAmountLabel.setText(String.valueOf(++movesAmount));
                    field.setFieldState(FieldState.PLAYER);
                    userPawnsToSetLabel.setText(String.valueOf(--userPawnsToSet));
                    if (board.catchUserMill()) {
                        userMillsLabel.setText(String.valueOf(++userMills));
                        removeEventHandlerFromAllFields();
                        setOnClickToComputerFieldsWhenPlayerMakesMillInFirstRound();
                    } else {
                        computerMoveInFirstRound();
                        colouredFields.add(getColouredFields());
                        saveGameParametersIntoLists();
                        boardNumber++;

                    }
                }
            });
        }
    }

    private void setOnClickToComputerFieldsWhenPlayerMakesMillInFirstRound() {
        List<Field> computerFieldsList = getAllComputerFields();

        for(Field field : computerFieldsList) {
            field.getFieldImage().setOnMouseClicked(event -> {
                field.setFieldState(FieldState.UNCHECKED);
                cpuPawnsLeftLabel.setText(String.valueOf(--cpuPawnsLeft));
                setOnClickToAllFieldsForFirstRound();
                computerMoveInFirstRound();

                colouredFields.add(getColouredFields());
                saveGameParametersIntoLists();
                boardNumber++;
            });
        }
    }

    private void removeEventHandlerFromAllFields() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();

        for(Field field : allFieldsList)
            field.getFieldImage().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    field.getFieldImage().removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
                }
            });
    }

    private List<Field> getAllComputerFields() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        List<Field> computerFieldsList = new ArrayList<>();

        for(Field field : allFieldsList)
            if(field.getFieldState() == FieldState.COMPUTER)
                computerFieldsList.add(field);

        return computerFieldsList;
    }

    private void computerMoveInFirstRound() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        allFieldsList.get(computerChoseFieldToPlacePawnInFirstRound()).setFieldState(FieldState.COMPUTER);
        cpuPawnsToSetLabel.setText(String.valueOf(--cpuPawnsToSet));
        if(board.catchCpuMill()) {
            cpuMillsLabel.setText(String.valueOf(++cpuMills));
            allFieldsList.get(computerChoseFieldToRemovePlayerPawn()).setFieldState(FieldState.UNCHECKED);
            userPawnsLeftLabel.setText(String.valueOf(--userPawnsLeft));
        }
        if(userPawnsToSet == 0) {
            removeEventHandlerFromAllFields();
            setOnClickToFindAvailableFieldsToAllFieldsForSecondRound();
        }
    }

    private int computerChoseFieldToPlacePawnInFirstRound() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        Random randomGenerator = new Random();
        int allFieldsListSie = allFieldsList.size();
        int randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);

        while (allFieldsList.get(randomFieldIndex).getFieldState() != FieldState.UNCHECKED)
            randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);

        return randomFieldIndex;
    }

    private int computerChoseFieldToRemovePlayerPawn() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        Random randomGenerator = new Random();
        int allFieldsListSie = allFieldsList.size();
        int randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);

        while (allFieldsList.get(randomFieldIndex).getFieldState() != FieldState.PLAYER)
            randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);

        return randomFieldIndex;
    }

    /*----------------SECOND ROUND---------------*/

    private void setOnClickToFindAvailableFieldsToAllFieldsForSecondRound() {
        List<Square> allSquaresList = board.getListOfAllSquares();
        List<Field> allFieldsFromAllSquares = board.getListOfAllFieldsFromAllSquares();

        for(Square square : allSquaresList) {
            List<Field> allFieldsList = square.getAllFields();
            for(Field field : allFieldsList) {
                if(field.getFieldState() == FieldState.PLAYER) {
                    field.getFieldImage().setOnMouseClicked(event -> {
                        deleteAllAvailableFieldsState();
                        List<FieldIndex> availableFieldsIndexes;
                        if(userPawnsLeft > 3 || cpuPawnsLeft > 3)
                            availableFieldsIndexes = field.getFieldIndex().getNeighborhood();
                        else
                            availableFieldsIndexes = board.getListOfFieldIndexes();
                        for (Field potentialAvailableField : allFieldsFromAllSquares) {
                            for (FieldIndex fieldIndex : availableFieldsIndexes) {
                                if (potentialAvailableField.getFieldIndex() == fieldIndex
                                        && potentialAvailableField.getFieldState() == FieldState.UNCHECKED) {
                                    potentialAvailableField.setFieldState(FieldState.AVAILABLE);
                                    setOnClickToMovePawnToAvailableFieldsForSecondRound(field);
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    private void setOnClickToMovePawnToAvailableFieldsForSecondRound(Field pawnToMove) {
        List<Field> allAvailableFields = board.getListOfAvailableFields();

        for (Field availableField : allAvailableFields) {
            availableField.getFieldImage().setOnMouseClicked(event -> {
                pawnToMove.setFieldState(FieldState.UNCHECKED);
                availableField.setFieldState(FieldState.PLAYER);
                deleteAllAvailableFieldsState();
                removeEventHandlerFromAllFields();
                movesAmountLabel.setText(String.valueOf(++movesAmount));
                if (board.catchUserMill()) {
                    userMillsLabel.setText(String.valueOf(++userMills));
                    setOnClickToComputerFieldsWhenPlayerMakesMillInSecondRound();
                }
                else {
                    computerMoveInSecondRound();
                    setOnClickToFindAvailableFieldsToAllFieldsForSecondRound();

                    colouredFields.add(getColouredFields());
                    saveGameParametersIntoLists();
                    boardNumber++;
                }
            });
        }
    }

    private void deleteAllAvailableFieldsState() {
        List<Field> allFieldsFromAllSquares = board.getListOfAllFieldsFromAllSquares();

        for(Field field : allFieldsFromAllSquares)
            if(field.getFieldState() == FieldState.AVAILABLE)
                field.setFieldState(FieldState.UNCHECKED);
    }

    private void setOnClickToComputerFieldsWhenPlayerMakesMillInSecondRound() {
        List<Field> computerFieldsList = getAllComputerFields();

        for(Field field : computerFieldsList) {
            field.getFieldImage().setOnMouseClicked(event -> {
                field.setFieldState(FieldState.UNCHECKED);
                cpuPawnsLeftLabel.setText(String.valueOf(--cpuPawnsLeft));
                computerMoveInSecondRound();
                setOnClickToFindAvailableFieldsToAllFieldsForSecondRound();

                colouredFields.add(getColouredFields());
                saveGameParametersIntoLists();
                boardNumber++;

                if(cpuPawnsLeft == 2) {
                    try {
                        showWinningMessage(true);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    board.disableAllFields();
                }
            });
        }
    }

    private void computerMoveInSecondRound() {
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        int fieldFromPawnIndex = computerFindPawnToMove();
        int fieldToPawnIndex = computerFindPlaceToMovePawn(allFieldsList.get(fieldFromPawnIndex));

        allFieldsList.get(fieldFromPawnIndex).setFieldState(FieldState.UNCHECKED);
        allFieldsList.get(fieldToPawnIndex).setFieldState(FieldState.COMPUTER);

        if(board.catchCpuMill()) {
            cpuMillsLabel.setText(String.valueOf(++cpuMills));
            allFieldsList.get(computerChoseFieldToRemovePlayerPawn()).setFieldState(FieldState.UNCHECKED);
            userPawnsLeftLabel.setText(String.valueOf(--userPawnsLeft));
        }
    }

    private int computerFindPawnToMove() { //ADD ARTIFICIAL INTELLIGENCE
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        Random randomGenerator = new Random();
        int allFieldsListSie = allFieldsList.size();
        int randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);
        List<FieldIndex> neighborhood = allFieldsList.get(randomFieldIndex).getFieldIndex().getNeighborhood();

        List<Field> realNeighborhood = new ArrayList<>();
        for(FieldIndex fieldIndex : neighborhood)
            if(board.getFieldByIndex(fieldIndex).getFieldState() == FieldState.UNCHECKED)
                realNeighborhood.add(board.getFieldByIndex(fieldIndex));

        while (allFieldsList.get(randomFieldIndex).getFieldState() != FieldState.COMPUTER
                || realNeighborhood.size() == 0) {
            randomFieldIndex = randomGenerator.nextInt(allFieldsListSie);

            neighborhood = allFieldsList.get(randomFieldIndex).getFieldIndex().getNeighborhood();
            realNeighborhood = new ArrayList<>();
            for(FieldIndex fieldIndex : neighborhood)
                if(board.getFieldByIndex(fieldIndex).getFieldState() == FieldState.UNCHECKED)
                    realNeighborhood.add(board.getFieldByIndex(fieldIndex));
        }

        return randomFieldIndex;
    }

    private int computerFindPlaceToMovePawn(Field fromFieldPawn) {
        List<FieldIndex> neighborhood = fromFieldPawn.getFieldIndex().getNeighborhood();
        List<Field> allFieldsList = board.getListOfAllFieldsFromAllSquares();
        int allFieldsListSize = allFieldsList.size();
        Random randomGenerator = new Random();
        int neighborIndex = randomGenerator.nextInt(neighborhood.size());

        while (board.getFieldByIndex(neighborhood.get(neighborIndex)).getFieldState() != FieldState.UNCHECKED)
            neighborIndex = randomGenerator.nextInt(neighborhood.size());

        for(int i = 0; i < allFieldsListSize; i++)
            if(allFieldsList.get(i).getFieldIndex() == neighborhood.get(neighborIndex))
                return i;

        System.out.println("Error in finding place to move pawn!");
        return -1;
    }

}
