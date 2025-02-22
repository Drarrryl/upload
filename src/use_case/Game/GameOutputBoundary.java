package use_case.Game;

public interface GameOutputBoundary {
    void prepareWinView();
    void prepareLoseView();
    void prepareSaveAndQuit(GameOutputData outputData);
}
