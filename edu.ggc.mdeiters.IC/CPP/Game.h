// Game.h
// @auther: Mike Deiters
// @version: 1.0

#ifndef Game_h
#define Game_h

class Game {

private:
  int idNumber;
  int numCopies;
  std::string itemName;
  std::string type;

public:
  Game(int idNumber, std::string itemName, std::string type, int numCopies);
  int getNumCopies();
  int getIdNumber();
  std::string getItemName();
  std::string getType();
  std::string toString();
  int compareTo(Game o);
};

#endif /* Game_h */
