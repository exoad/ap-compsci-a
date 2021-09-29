<div style="color:green;">AP Computer Science</div>
<div style="color:green;">Introductory Project</div>


<center><u>Quadrilaterals</u> (55 points)</center>
<br>
<p>
  The purpose of this project is to determine the type of a quadrilateral based upon its points on the coordinate
  plane. Your project must include a driver class (<code>QuadrilateralDriver</code>), but must also work with my
  driver. User input and output must be handled in the driver class, and you are permitted to use any method of
  doing so here (i.e., <code>Scanner</code>, <code>JOptionPane</code>, full GUI).</p>
<p>
  Your project must include the following classes, and must include (at least) the methods and fields listed.</p>
<u>Class Point</u><br>
<strong>
  Fields:
</strong>
<br>
<code>
double x, double y
</code><br>
<strong>Methods:</strong><br>
accessor and mutator methods for all fields
constructor that accepts two double parameters
<code>
double distance (Point p)<br>
boolean equals (Point p)<br>
Point midpoint (Point p)<br>
double slope (Point p)<br>
String toString()<br>
static boolean approx(double a, double b)<br>
</code><br>
<u>Class Quadrilateral</u><br>
<strong>Fields:</strong>
array of 4 Point objects to store the coordinates of the vertices<br>
<strong>Methods:</strong>
accessor and mutator methods for all fields
constructor that accepts 4 Point objects
classification methods for each quadrilateral (trapezoid, parallelogram, rectangle, rhombus, square)<br>
i.e., <code>boolean isTrapezoid() <br>
String toString() <br>
static boolean approx(double a, double b) <br></code><br>
Your project must follow all standard java conventions (naming, style, indentation, etc.). All data must be fully
encapsulated, and all methods must have appropriate visibility modifiers. Your code MUST BE
COMMENTED.
</p>
<hr>
<p><u>Bonus Methods</u> (if you finish early):<br>
  Add some or all of the following transformation methods to the <code>Point</code> class. They should not modify the
  original <code>Point</code>, but rather they should return a new Point object which represents the result of applying
  the
  given transformation to the given <code>Point</code>. They get harder as you go down the list.<br>
  <code>Point translate(double deltaX, double deltaY)
    
Point dilate(double factor)   //dilates by given factor, center at origin
    
Point reflectYEqualsX()                 //reflects across line y = x
    
Point reflectHorizontal(double xValue)  //reflects across line x = xValue
    
Point reflectVertical(double yValue)    //reflects across line y = yValue
    
Point rotate(int angle) //angle must be a multiple of 90°
    
Point dilate(double factor, Point center) //center of dilation variable
    
Point rotate(double angle) //rotates by angle (degrees) around origin
    
Point rotate(double angle, Point center) //rotates by angle around center
    
Point reflect(Point a, Point b) //reflects across line through a and b
    
<code>
</p>
