// game.ccp
// @author: Mike Deiters
// @version: 1.0

#include <iostream>
#include <sstream>
#include "Game.h"

using namespace std;

Game::Game(int idNumber, std::string itemName, std::string type, int numCopies) {
  Game::idNumber = idNumber;
  Game::itemName = itemName;
  Game::type = type;
  Game::numCopies = numCopies;
}

int Game::getNumCopies() {
  return numCopies;
}

int Game::getIdNumber() {
  return idNumber;
}

std::string Game::getItemName() {
  return itemName;
}

std::string Game::getType() {
  return type;
}

std::string Game::toString() {
  std::stringstream str;
  str << "Game: idNumber= " << idNumber << ", numCopies= " << numCopies << ", itemName= " << itemName << ", type= " << type;
  return str.str();
}

int Game::compareTo(Game o) {
  int returnValue;
  if (itemName<o.getItemName()) {
    returnValue = -1;
  } else if (itemName>o.getItemName()) {
    returnValue = 1;
  }
  if (returnValue == 0) {
    if (type<o.getType()) {
      returnValue = -1;
    } else if (type>o.getType()) {
      returnValue = 1;
    }
  }
  return returnValue;
}

int main(int argc, const char * argv[]) {
  Game game (1, "MineCraft", "PC", 42);
  Game g2 (2, "BattleFront", "PS4", 9001);
  Game g3 (3, "Bloodborne", "PS4", 3);
  cout << game.toString() << "\n";
  cout << g2.toString() << "\n";
  cout << g3.toString() << "\n";
  return 0;
}
