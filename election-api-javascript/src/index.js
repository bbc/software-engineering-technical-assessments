const { default: server } = require("./server");
const port = process.env.PORT || 3000;

server.listen(port, () => {
  console.log(`Election app listening at http://localhost:${port}`);
});
