//
//  Person.h
//  DemoProject
//
//  Created by cjohns25 on 11/17/15.
//  Copyright © 2015 cjohns25. All rights reserved.
//

#ifndef Person_h
#define Person_h

class Person
{
private:
    std::string firstName;
    std::string lastName;
    int idNum;
    std::string city;

public:
    Person();
    Person(std::string fName, std::string lName, int num, std::string newCity);
    std::string getFirstName();
    std::string getLastName();
    std::string getCity();
    int getID();
    void printPerson();
};

#endif /* Person_h */
