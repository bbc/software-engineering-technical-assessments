type PartyResult = {
  party: string;
  votes: number;
  share: number;
};

export type Result = {
  id: number;
  name: string;
  seqNo: number;
  partyResults: PartyResult[];
};
