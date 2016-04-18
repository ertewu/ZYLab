package zystudio.demo.protobuf;

import java.io.FileInputStream;

import zystudio.demo.protobuf.AddressBookProtos.AddressBook;
import zystudio.demo.protobuf.AddressBookProtos.Person;

public class ListPeople {

    static void Print(AddressBook addressBook) {

        for (Person person : addressBook.getPersonList()) {
            System.out.println("Person ID:" + person.getId());
            System.out.println("Name:" + person.getName());
            if (person.hasEmail()) {
                System.out.println("E-mail address:" + person.getEmail());
            }

            for (Person.PhoneNumber phoneNumber : person.getPhoneList()) {
                switch (phoneNumber.getType()) {
                case MOBILE:
                    System.out.print("Mobile phone #:");
                    break;
                case HOME:
                    System.out.print("Home phone #:");
                    break;
                case WORK:
                    System.out.print("Work phone #:");
                    break;
                }
                System.out.println(phoneNumber.getNumber());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: ListPeople ADDRESS_BOOK_FILE");
            System.exit(-1);
        }

        // Read the existing address book
        AddressBook addressBook = AddressBook.parseFrom(new FileInputStream(args[0]));
    }

}
