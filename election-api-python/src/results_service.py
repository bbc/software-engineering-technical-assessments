class ResultStore:

    def __init__(self) -> None:
        self.store: list[dict] = []
    
    def get_result(self, id_to_get) -> dict:
        result: list[dict] = list(filter(lambda result: result['id'] == int(id_to_get), self.store))
        return result[0] if len(result) > 0 else {}

    def new_result(self, result) -> None:
        self.store.append(result)

    def get_all(self) -> list[dict]:
        return self.store

    def reset(self) -> None:
        self.store: list[dict] = []