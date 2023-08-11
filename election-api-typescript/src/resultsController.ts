import resultStore from "./resultsService";
import { Result } from "./types";

const store = resultStore();

const getResult = (id: number): Result | null => {
  return store.getResult(id);
};

const newResult = (result: Result) => {
  store.newResult(result);
  console.log("Store created successfully.");
  return null;
};

const reset = (): void => {
  store.reset();
};

const scoreboard = () => {
  //  Left blank for you to fill in
};

export { getResult, newResult, reset, scoreboard };
