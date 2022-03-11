from results_service import ResultStore

class ResultsController:

    def __init__(self) -> None:
        self.store: ResultStore = ResultStore()
    
    def get_result(self, id: int) -> dict:
        return self.store.get_result(id)
    
    def new_result(self, result: dict) -> dict:
        self.store.new_result(result)
        return {}
    
    def reset(self) -> None:
        self.store.reset()
    
    def scoreboard(self) -> None:
        return ""

    # DEBUG
    def get_all(self) -> list[dict]:
        return self.store.get_all()