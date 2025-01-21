from results_service import ResultStore

class ResultsController:

    def __init__(self) -> None:
        self.result_store: ResultStore = ResultStore()

    def get_result(self, identifier: int) -> dict:
        return self.result_store.get_result(identifier)

    def new_result(self, result: dict) -> dict:
        self.result_store.new_result(result)
        return {}

    def reset(self) -> None:
        self.result_store.reset()

    def scoreboard(self) -> dict:
        # Left blank for you to fill in
        return {}
