import resultStore from "./resultsService";
import { Result } from "./types";

const store = resultStore();

const getResult = (id: number): Result | null => {
  return store.getResult(id);
};

// TODO: Ask someone why this returns empty object instead of result and add type
const newResult = (result: Result) => {
  store.newResult(result);
  return {};
};

const reset = (): void => {
  store.reset();
};

const scoreboard = () => {
  //  Left blank for you to fill in
};

export { getResult, newResult, reset, scoreboard };
