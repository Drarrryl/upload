package use_case.Game;

public interface GameInputBoundary {
    void save(GameInputData data);
    void saveAndQuit(GameInputData data);
}
