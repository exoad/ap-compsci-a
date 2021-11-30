package resource.classworks.nov19;

public class Student implements Comparable {
    String name;
    int id;
    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return id;
    }
    /**
     * <p>This method attempt to compare the length of the name and the id value
     * @param s1 An object reprensenting a Student Object
     * @return Ints of -1, 1, 0 representing the state 
     */
    @Override
    public int compareTo(Object s1) {
      Student student = (Student) s1;
      /* If the name length of this instance is always smaller it will always return -1 */
      if((name.length() < student.getName().length() && id < student.getID()) || (name.length() < student.getName().length() && id > student.getID()))
        return -1;
      /* If the name length is always bigger than the other instance it will always return 1*/
      if((name.length() > student.getName().length() && id > student.getID()) || (name.length() > student.getName().length() && id < student.getID())) 
        return 1;
      // if they are equal
      return 0;
      
    }
}