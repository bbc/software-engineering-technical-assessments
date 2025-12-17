import { Result } from "./types";

const resultStore = () => {
  const store: Result[] = [];

  const getResult = (idToGet: number): Result | undefined => {
    return store.find(({ id }) => id === idToGet);
  };

  const newResult = (result: Result): void => {
    store.push(result);
  };

  const getAll = (): Result[] => {
    return store;
  };

  const reset = (): void => {
    store.splice(0);
  };

  return {
    getResult,
    newResult,
    getAll,
    reset,
  };
};

export default resultStore;
