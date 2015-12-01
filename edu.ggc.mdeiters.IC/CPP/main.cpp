//
//  main.cpp
//  DemoProject
//
//  Created by cjohns25 on 11/17/15.
//  Copyright © 2015 cjohns25. All rights reserved.
//

#include <iostream>
#include "Person.h"


Person::Person()
{
    firstName = "";
    lastName = "";
    idNum = 0;
    city = "";
}

 Person::Person(std::string fName, std::string lName, int num, std::string newCity)
{
    firstName = fName;
    lastName = lName;
    idNum = num;
    city = newCity;
    
}

std::string Person::getFirstName()
{
    return firstName;
}

std::string Person::getLastName()
{
    return lastName;
}

int Person::getID()
{
    return idNum;
}

std::string Person::getCity()
{
    return city;
}

void Person::printPerson()
{
    std::cout << firstName << " " << lastName << " id = " << idNum << " city = " << city<<"\n";
    
}

int main(int argc, const char * argv[]) {
   
    Person p ("General", "Grizzly", 1, "Lawrenceville");
    p.printPerson();
    
    return 0;
}
