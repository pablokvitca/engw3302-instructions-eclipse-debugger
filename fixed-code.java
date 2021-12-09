import tester.*;

interface IPerson {
  
  public String getName();
  
  public String info();
  
}

class Unknown implements IPerson {
  
  @Override
  public String getName() {
    return "Unknown";
  }
  
  
  @Override
  public String info() {
    return "N/A";
  }
  
}

class Person implements IPerson {
  
  String name;
  int age;
  IPerson mother;
  IPerson father;
  
  Person(String name, int age, IPerson mother, IPerson father) {
    this.name = name;
    this.age = age;
    this.mother = mother;
    this.father = father;
  }
  
  Person(String name, int age) {
    this(name, age, new Unknown(), new Unknown());
  }
  
  @Override
  public String getName() {
    return this.name;
  }
  
  @Override
  public String info() {
    return String.format(
        "%s (%d), child of %s and %s", 
        this.name, 
        this.age,
        this.mother.getName(),
        this.father.getName()
        );
  }  
  
}

class ExamplesPerson {
  
  IPerson unknown = new Unknown();
  IPerson paul = new Person("Paul", 42);
  IPerson steve = new Person("Steve", 45);
  IPerson martha = new Person("Martha", 27, unknown, paul);
  IPerson ashley = new Person("Ashley", 8, martha, steve);
  
  void testGetName(Tester t) {
    t.checkExpect(this.unknown.getName(), "Unknown");
    t.checkExpect(this.paul.getName(), "Paul");
    t.checkExpect(this.steve.getName(), "Steve");
    t.checkExpect(this.martha.getName(), "Martha");
    t.checkExpect(this.ashley.getName(), "Ashley");
  }
  
  void testInfo(Tester t) {
    t.checkExpect(this.unknown.info(), "N/A");
    t.checkExpect(this.paul.info(), "Paul (42), child of Unknown and Unknown");
    t.checkExpect(this.steve.info(), "Steve (45), child of Unknown and Unknown");
    t.checkExpect(this.martha.info(), "Martha (27), child of Unknown and Paul");
    t.checkExpect(this.ashley.info(), "Ashley (8), child of Martha and Steve");
  }
  
}