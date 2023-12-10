#ifndef EMPLOYEE_H
#define EMPLOYEE_H

struct Employee {
    int num;
    char name[50];
    double hours;

    // Serialization method for the struct
    template <class Archive>
    void serialize(Archive& ar, const unsigned int version) {
        ar & num & name & hours;
    }
};

#endif // EMPLOYEE_H
