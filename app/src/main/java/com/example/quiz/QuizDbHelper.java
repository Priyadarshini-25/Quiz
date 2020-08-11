package com.example.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quiz.QuizContract.*;

import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizDbHelper instance;
    private SQLiteDatabase db;
    private QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Category c1 = new Category("Programming");
        insertCategory(c1);
        Category c2 = new Category("Math");
        insertCategory(c2);
        Category c3 = new Category("Words");
        insertCategory(c3);
    }
    public void addCategory(Category category) {
        db = getWritableDatabase();
        insertCategory(category);
    }
    public void addCategories(List<Category> categories) {
        db = getWritableDatabase();
        for (Category category : categories) {
            insertCategory(category);
        }
    }
    private void insertCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("A process that involves recognizing and focusing on the important characteristics of a situation or object is known as:",
                "Polymorphism", "Abstraction", "Encapsulation", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q1);
        Question q2 = new Question("The wrapping up of data and functions into a single unit is called",
                "Data Hiding", "Polymorphism", "Encapsulation", 3,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q2);
        Question q3 = new Question(" In object-oriented programming, new classes can be defined by extending existing classes. This is an example of:",
                "Aggregation", "Interface ", "Composition", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q3);
        Question q4 = new Question("Given a class named student, which of the following is a valid constructor declaration for the class?",
                "Student (student s) { } ", "Student student ( ) { }", "Void student ( ) { }", 1,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q4);
        Question q5 = new Question(" Which of these field declarations are legal within the body of an interface?",
                " final static answer =42", "public static int answer=42", "Private final static int answer = 42", 2,
                Question.DIFFICULTY_EASY, Category.PROGRAMMING);
        insertQuestion(q5);
        Question q6 = new Question("A package is a collection of",
                "Classes and interfaces", "Classes", "Classes and objects", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q6);
        Question q7 = new Question(" Basic Java language functions are stored in which of the following java package?",
                "java.lang", "java.util", "java.io", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q7);
        Question q8 = new Question("Which of the following is a member of the java.lang package?",
                "Queue", "List", "Stack", 1,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q8);
        Question q9 = new Question(" Which of the following has a method names flush( )?",
                "Reader stream", "Input output stream", "Output Stream", 3,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q9);
        Question q10 = new Question(" Which one of the following class definitions is a valid definition of a class that cannot be extended?",
                "class Link { }", "final class Link { }", "static class Link { }", 2,
                Question.DIFFICULTY_MEDIUM, Category.PROGRAMMING);
        insertQuestion(q10);
        Question q11 = new Question(" How restrictive is the default accessibility compared to public, protected, and private accessibility?",
                "Less restrictive than public", "More restrictive than private", "More restrictive than protected, but less restrictive than private", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q11);
        Question q12 = new Question(" Which one of these is a valid method declaration?",
                "void method3(void)", "method4()", "void method2()", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q12);
        Question q13 = new Question(" What is the return type of the method getID() defined in AWTEvent class",
                "float", "Long", " Int", 3,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q13);
        Question q14 = new Question(" What is the sequence of major events in the life of an applet?",
                "init, start, stop, destroy", "init, start , destroy, stop", " destroy, start, init, stop.", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q14);
        Question q15 = new Question(" Given the code\n" +
                "\n" +
                "String s1 = ” yes” ;\n" +
                "String s2 = ” yes ” ;\n" +
                "String s3 = new String ( s1);",
                " s1 == s2", "s3=s1", "s3==s1", 1,
                Question.DIFFICULTY_HARD, Category.PROGRAMMING);
        insertQuestion(q15);
        Question q16 = new Question("Type the next number in this sequence:\n" +
                "\n" +
                "0,    1,    1,    2,    3,    5,",
                "16", "10", "8", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q16);
        Question q17 = new Question("0!=",
                "0", "1", "undefined", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q17);
        Question q18 = new Question("The areas of two similar triangles are 81 sq. cm and 49 sq. cm. Find the ratio of their corresponding heights.",
                "81:49", " 9:7", "7:9", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q18);
        Question q19 = new Question("101+10=?",
                "101", "111", "110", 2,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q19);
        Question q20 = new Question("100%10=? (%=mod)",
                "10", "1", "0", 3,
                Question.DIFFICULTY_EASY, Category.MATH);
        insertQuestion(q20);
        Question q21 = new Question("Evaluation of 8^3 × 8^2 × 8^-5 is ",
                "8", "1", "0", 2,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q21);
        Question q22 = new Question("The simplest form of 1.5 : 2.5 is",
                "3:5", "5:3", "15:25", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q22);
        Question q23 = new Question("A number is divisible by 5 if its unit digit is",
                " 0 or 5", "2 or 0", "10 or 0", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q23);
        Question q24 = new Question(" Average of three person’s age is 9 years. Find the sum of there age.",
                "18", "21", "27", 3,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q24);
        Question q25 = new Question("1010 gram = __ kg.",
                "1.01 kg.", "10.10 kg.", "101.0 kg.", 1,
                Question.DIFFICULTY_MEDIUM, Category.MATH);
        insertQuestion(q25);
        Question q26 = new Question(" The arithmetic mean between a and 10 is 30, the value of ‘a’ should be",
                "70", "-20", "50", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q26);
        Question q27 = new Question("d/dx(8x-2)=?",
                "2", "0", "8", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q27);
        Question q28 = new Question("d/dx(e^x)=?",
                "1", "e^x", "0", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q28);
        Question q29 = new Question("f(x)=x*x-x, find f(4)",
                "9", "16", "12", 3,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q29);
        Question q30 = new Question("f(x)=6x-3 find f'(3)",
                "0", "6", "15", 2,
                Question.DIFFICULTY_HARD, Category.MATH);
        insertQuestion(q30);
        Question q31 = new Question("REITERATE",
                "REPEAT", "FRUSTRATE", "ILLUSTRATE", 1,
                Question.DIFFICULTY_EASY, Category.WORDS);
        insertQuestion(q31);
        Question q32 = new Question("EMULATE",
                "DISCUSS", "DENY", "IMITATE", 3,
                Question.DIFFICULTY_EASY, Category.WORDS);
        insertQuestion(q32);
        Question q33 = new Question("REPERCUSSION",
                "FAULT", "CHAIN OF CONSEQUENCES", "OUTCOME", 2,
                Question.DIFFICULTY_EASY, Category.WORDS);
        insertQuestion(q33);
        Question q34 = new Question("AMALGATE",
                "UNITE", "ASTONISH", "GATHER", 1,
                Question.DIFFICULTY_EASY, Category.WORDS);
        insertQuestion(q34);
        Question q35 = new Question("STRIDENT",
                "MUSICAL", "STABLE", "PLEASANT", 3,
                Question.DIFFICULTY_EASY, Category.WORDS);
        insertQuestion(q35);
        Question q36 = new Question("Managers set objectives, and decide _____ their organization can achieve them.",
                "what", "when", "how", 3,
                Question.DIFFICULTY_MEDIUM, Category.WORDS);
        insertQuestion(q36);
        Question q37 = new Question("Warning! No unauthorised personnel _____ this point. ",
                "under", "beyond", "behind", 2,
                Question.DIFFICULTY_MEDIUM, Category.WORDS);
        insertQuestion(q37);
        Question q38 = new Question("You ______ fly to Dover – there isn’t an airport. ",
                "cannot", "may", "when", 1,
                Question.DIFFICULTY_MEDIUM, Category.WORDS);
        insertQuestion(q38);
        Question q39 = new Question("TO DECLARE INVALID:",
                "RENEGE", "NULLIFY", "DECAMP", 2,
                Question.DIFFICULTY_MEDIUM, Category.WORDS);
        insertQuestion(q39);
        Question q40 = new Question("an overabundance of words",
                "DOLDRUMS", "VERBIAGE", "EXIGENCY", 2,
                Question.DIFFICULTY_MEDIUM, Category.WORDS);
        insertQuestion(q40);
        Question q41 = new Question("to manage to remain alive",
                "WINNOW", "LAMENT", "SUBSIST", 3,
                Question.DIFFICULTY_HARD, Category.WORDS);
        insertQuestion(q41);
        Question q42 = new Question("to hold spellbound",
                "ENTHRALL", "ARTICULATE", "BELEAGUER", 1,
                Question.DIFFICULTY_HARD, Category.WORDS);
        insertQuestion(q42);
        Question q43 = new Question("to calm someone's emotions",
                "HECTOR", "ALLAY", "DECAMP", 2,
                Question.DIFFICULTY_HARD, Category.WORDS);
        insertQuestion(q43);
        Question q44 = new Question("to hint; to creep in",
                "PILLORY", "ABRIDGE", "INSINUATE", 3,
                Question.DIFFICULTY_HARD, Category.WORDS);
        insertQuestion(q44);
        Question q45 = new Question("producing no result or effect",
                "COMTEMPORARY", "ARID", "FUTILE", 3,
                Question.DIFFICULTY_HARD, Category.WORDS);
        insertQuestion(q45);

    }

    public void addQuestion(Question question) {
        db = getWritableDatabase();
        insertQuestion(question);
    }
    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
        }
    }
    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
