package com.example.assignment2.Database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.assignment2.Dao.ContactDao;
import com.example.assignment2.Model.Contact;
@Database(
        entities = {Contact.class},
        version = 1,
        exportSchema = false
)
public abstract class PhonebookDb extends RoomDatabase{

    private final String TAG = this.getClass().getSimpleName();

    public abstract ContactDao contactDao();

    private static PhonebookDb phonebookDb;

    public static PhonebookDb getInstance(final Context context)
    {
        if(phonebookDb == null) //If holmesglenDb does not exist, create the DB
        {
            phonebookDb = Room.databaseBuilder(
                    context.getApplicationContext(),
                    PhonebookDb.class,
                    "phonebook_room.db"
            ).allowMainThreadQueries().build();
        }
        return  phonebookDb;
    }

    public static int initData(final Context context){
        PhonebookDb db = getInstance(context);
        if(db.contactDao().getAllContacts().size() == 0) {
            db.contactDao().insert(
        new Contact("J Smith", "JSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("L Smith", "LSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("W Smith", "WSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("A Smith", "ASmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("Z Smith", "ZSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("B Smith", "BSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("G Smith", "GSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("S Smith", "SSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("T Smith", "TSmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("U Smith", "USmith@gmail.com", "123 456 789","01/01/2000"),
        new Contact("Q Smith", "QSmith@gmail.com", "123 456 789","01/01/2000")


            );
        }
        return db.contactDao().getAllContacts().size();
    }
}


//    // static variable single_instance of type Singleton
//    private static PhonebookDb db_instance = null;
//
//    // variable of type ArrayList
//    private ArrayList<Contact> phonebook;
//
//    // private constructor restricted to this class itself
//    private PhonebookDb()
//    {
//        phonebook = new ArrayList<Contact>();
//
//        // init data
//        phonebook.add(new Contact("J Smith", "JSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("L Smith", "LSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("W Smith", "WSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("A Smith", "ASmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("Z Smith", "ZSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("B Smith", "BSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("G Smith", "GSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("S Smith", "SSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("T Smith", "TSmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("U Smith", "USmith@gmail.com", "123 456 789","01/01/2000"));
//        phonebook.add(new Contact("Q Smith", "QSmith@gmail.com", "123 456 789","01/01/2000"));
//
//    }
//
//    @NonNull
//    @Override
//    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
//        return null;
//    }
//
//    @NonNull
//    @Override
//    protected InvalidationTracker createInvalidationTracker() {
//        return null;
//    }
//
//    @Override
//    public void clearAllTables() {
//
//    }
//
//    public int count() {
//        return phonebook.size();
//    }

//    // static method to create instance of Singleton class
//    public static PhonebookDb getInstance()
//    {
//        if (db_instance == null)
//            db_instance = new PhonebookDb();
//
//        return db_instance;
//    }

//    public void update(int id, Contact contact) {
//        if (id >= 0 && id < phonebook.size()){
//
//            phonebook.get(id).setName(contact.getName());
//            phonebook.get(id).setEmail(contact.getEmail());
//            phonebook.get(id).setPhone(contact.getPhone());
//            phonebook.get(id).setDate(contact.getDate());
//        }
//        return;
//    }
//
//    public int getIndex(Contact c)
//    {
//        int pos = -1;
//        for(int i =0; i<phonebook.size(); i++)
//        {
//            if(c.getName().equalsIgnoreCase(phonebook.get(i).getName()))
//            {
//                pos = i;
//                break;
//            }
//        }
//        return pos;
//    }
//
//    public void delete(int id) {
//        if (id >= 0 && id < phonebook.size()) {
//            phonebook.remove(id);
//        }
//        return;
//    }
//
//    // add a element to the end of list
//    public void add(Contact newContact) {
//        phonebook.add(newContact);
//        return;
//    }
//
//    // get a element to the end of list
//    public Contact get(int index) {
//        Contact c = null;
//        if(index >= 0 && index < phonebook.size()) {
//            c = phonebook.get(index);
//        }
//        return c;
//    }
//
//    public ArrayList<Contact> getAll() {
//        return (ArrayList<Contact>)phonebook.clone();
//    }



//    // dump db data to logcat for debug purpose
//    public void dump() {
//        System.out.println("Dump phonebook data ");
//        if (phonebook == null) {
//            System.out.println("db not initialized");
//        } else {
//            System.out.println("number of contact: " + phonebook.size());
//            for (int i = 0; i < phonebook.size(); i++){
//                Contact c = phonebook.get(i);
//                System.out.println(c.toString());
//            }
//        }
//        return;
//    }

