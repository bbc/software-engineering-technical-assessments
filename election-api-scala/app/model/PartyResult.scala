package model

case class PartyResult(party: String, votes: Int, share: BigDecimal)
case class ConstituencyResult(id: Int, name: String, seqNo: Int, partyResults: Seq[PartyResult])

case class ApiResponse (error: String, message: String)

// TODO: this class should hold:
//  - the overall winner (if there is one)
//  - the seats that each party wins in Parliament
case class Scoreboard(winner: String)