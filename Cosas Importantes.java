#RISTRAS Y EXPRESIONES REGULARES ( STRINGS Y REGEX)
{
	
#Contar caracteres
public static int count(String s1, char c){
        int res=0;
        for(int i = 0;i<s1.length(); i++){
            if(s1.charAt(i)==c){
                res++;    
            }
        }
        return res;
    }	
	
#Contar vocales
public static int vocales(String s1){
        int res=0;
        String vocales = "AEIUOaeiouÁÉÍÓÚáéíóúÜü";
        for(int i=0;i<s1.length();i++){
            for(int j=0;j<vocales.length();j++){
                if(s1.charAt(i)==vocales.charAt(j))res++;
            }
        }
        return res;
    }
	
#Obtener mes
public static String mes(String fecha){
        String[] res = fecha.split("-");
        return res[1];
    }

##ETIQUETADOR Dificil
public static String tagger(String s1, String s2){
        String result = "";
        boolean flag = false; //creamos una flag (indicador)
        for (int i = 0; i < s1.length(); i++){
            if (s2.indexOf(s1.charAt(i)) > -1){ //si se encuentra un carácter de la segunda frase, hacer
                if (!flag){ //si el indicador es falso, es decir, que no hemos pasado por aquí antes, añadiremos un
                    result += "[target]" + s1.charAt(i);
                    flag = true;    //y cambiamos el estado del indicador
                } else {
                    result += s1.charAt(i); //si el indicador es verdadero, se copia tal cual
                }
            } else { //si no se encuentra el carácter, entonces
                if (flag){  //si el indicador es positivo, es decir, terminamos de estar aquí hacer
                    result += "[endtarget]" + s1.charAt(i); 
                    flag = false; //y cambiamos el estado del indicador
                } else { //si el indicador es falso, copiar tal cual
                    result += s1.charAt(i);
                }
            }
        }
        if (flag){  //si el indicador es verdadero, (última palabra), hacer
            result = result + "[endtarget]";
        }
        
        return result; //devolver resultado
    }
	
#ETIQUETADOR menos dificil
public static String tagger(String s1, String s2) {
        String res = "";
        Pattern p = Pattern.compile("["+s2+"]+");
        Matcher m = p.matcher(s1);
        int pos = 0;
        while(m.find()){
            res += s1.substring(pos,m.start());
            res += "[target]"+m.group()+"[endtarget]";
            pos = m.end();
        }
        res += s1.substring(pos);
        return res;
    }

#Normalizar espacios
public static String normalize(String s1){
	
        String res = "";
        res=s1.replaceAll("[\\s]+"," ");
        return res.trim();
    }

#Obtener meses
public static String getMonths(String s1){
        String res = "";
        Pattern p = Pattern.compile("(\\d{2})-(enero||febrero||marzo||abril||mayo||junio||julio||agosto||septiembre||octubre||noviembre||diciembre)-(\\d{4})");
        Matcher m = p.matcher(s1);
        while(m.find()){
            res+= m.group(2) + "-";
        }
        return res.substring(0,res.length()-1);
    }

#Contar subristras
public static int count(String s1, String [] s2){
        int res = 0;    //variable a devolver
        for (int i = 0; i < s2.length; i++){    //recorremos el String array
            
            Pattern n = Pattern.compile(s2[i]); //Creamos un pattern para cada subristra
            Matcher m = n.matcher(s1);  //Y un matcher en la primera String dada
            
            while (m.find()){   //Cada vez que encuentre la substring en la String
                res++;  //Sumamos a res
            }
        }
        
        return res; //Y devolvemos 
    }

}

#RECURSIVIDAD
{
#Contar caracteres
public static int count(String s1, char a){
        //Si la ristra que tenemos está vacía
        if (s1.length() == 0){
            //Devolvemos 0
            return 0;
        }
        
        //Creamos una segunda string s2 que sea una subristra de la String s1
        String s2 = s1.substring(1);
        //Si el carácter que tenemos es el del parámetro
        if (s1.charAt(0) == a){
            //Devolvemos la cantidad de veces que tenemos el parámetro
            return count(s2, a) + 1;
        } else {
            //Si no, que se repita el proceso
            return count(s2, a);
        }
    }

#Contar vocales
public static int vocales(String s1){
        //Creamos una string con todas las vocales que pueden sumarse
        String vocales = "AEIUOaeiouÁÉÍÓÚáéíóúÜü";
        //Si la ristra que recibimos está vacía
        if (s1.length() == 0){
            //Devolvemos 0
            return 0;
        }
        
        //Si la String the vocales contiene el carácter que se encuentra en la
        //posición inicial de la String parámetro
        if (vocales.contains(Character.toString(s1.charAt(0)))){
            //Devolvemos la cantidad de vocales que contiene la ristra
            return 1 + vocales(s1.substring(1));
        } else {
            //Si no, repetimos el proceso
            return vocales(s1.substring(1));
        }
}		

#Invertir un array
private static void arrayaux (int[] a, int i, int j){
        /* Condición para salir de la recursión es que i (que empieza en 0, índice)
        del array sea menor que el índice final (que se va decrementando) */
        if (i < j){
            /*Creamos una variable temporal que nos guardará el número que se encuentra
            en el índice i del array inicial*/
            int b = a[i];
            //Que nos lo ponga al final
            a[i] = a[j];
            //Y luego el final será b
            a[j] = b;
            /*Y volvemos a llamar a la función, incrementando el valor de i y decrementando
            el valor de j.*/
            arrayaux(a, ++i, --j);
		}
	}  
public static void arrayReverse (int[] a){
        arrayaux(a, 0, a.length-1);
}
	
#La palabra es palindrom
public static boolean isPalindrome(String s1){
        /*  Si la longitud de la String es menor que dos, será capicúa, porque
            tamaño 1 < 2 => "a" */
        if (s1.length() < 2){
            return true;
        }
        
        /*  Si la letra que se encuentra al principio de la ristra es igual
            que la que se encuentra al final, continuará siendo capicúa, pero
            se tiene que cumplir siempre. Si no lo es (caso aquí), devolvemos
            falso.  */
        if (s1.charAt(0) != s1.charAt(s1.length() - 1)){
            return false;
        }
        
        /*  Si no entra en la segunda condición, será que se cumple que tengan
        las mismas letras al principio y al final, luego repetimos el proceso hasta que su
        longitud sea menor que uno. */
        return isPalindrome(s1.substring(1, s1.length() - 1));
    }

#Producto de arrays
public static int[] arrayProd(int[] a, int[] b, int c){
        int[] res = new int[c];
        /* Llamamos a un método auxiliar con 4 parámetros, los dos arrays que nos dan,
        el array resultante, y el número de elementos */
        arrayaux(a, b, res, c);
        return res;
    }
private static void arrayaux(int[] a, int[] b, int[] res, int c){
        /* Cuando lleguemos al último índice del array (índice 0), dejaremos de
        llamar a la función, mientras no lo hagamos, lo seguiremos haciendo */
        if (c == 1){
            res[c-1] = a[c-1] * b[c-1];
        } else {
            res[c-1] = a[c-1] * b[c-1];
            arrayaux(a, b, res, c-1);
        }
    }
	
##Pasar de decimal a binario
public static String toBinary(int n){
        int r=0;
        return aux(n,r);
}
    public static String aux(int n, int r){
        if(n<2)return Integer.toString(n);
        else{
            r=n%2;
            return  aux(n/2,r) + Integer.toString(r);
             
        }
    }

#Ejercicio de examen multiplicar vectores de un determinado tamaño
public static int[] aitor(int[] v1,int[] v2,int n){
        int[] res;
        if(v1.length<v2.length){
            res = new int[v2.length];
        }
        if(v2.length<v1.length){
            res = new int[v1.length];
        }
        else{
            res = new int[v2.length];
        }
        return aux(v1, v2, n, res); 
    }    
    
public static int[] aux(int[] v1,int[] v2,int n, int[] res){
        if(n==0)return res;
        else{
            res[n-1]= v1[n-1]*v2[n-1];
            return aux(v1, v2,n-1,res);
        }
    }

}

#POO (MODULOS: Paquetes y Clases)
{
##Ejercicio RATIONAL :
{
public class Rational implements Comparable <Rational>, Cloneable {
    private int num;
    private int den;
    
    // Constructor
    public Rational(int n, int d){
        // Reducimos la fracción
        int x = mcd(n, d);
        num = n / x;
        den = d / x;
    }
    
    // MCD (reducir fracciones a su forma canónica)
    private static int mcd(int m, int n){
        if (n == 0){
            return m;
        } else {
            return mcd(n, m%n);
        }
    }
    
    // Método suma
	//(Suma el Rational this con el Rational pasado como parámetro)
    public Rational add(Rational a){
        int numerator = (this.num * a.den) + (this.den * a.num);
        int denominator = (this.den * a.den);
        
        return new Rational(numerator, denominator);
    }
    
    // Método producto 
	//(Multiplica el Rational this por el Rational pasado como parámetro)
    public Rational prod(Rational b){
        int numerator = (this.num * b.num);
        int denominator = (this.den * b.den);
        
        return new Rational(numerator, denominator);
    }
    
    // Método ver si son iguales (equivalentes)
    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Rational)){
            return false;
        }
        Rational b = (Rational) obj;
        return compareTo(b) == 0;
    }
    
    // Método toString
	//(Pasar de Rational a String)
    @Override
    public String toString(){
        return String.valueOf(this.num) + "/" + String.valueOf(this.den);
    }
    
    // Método comparar
	//Si ->(a d = c b) son iguales, devuelve 0
	//Si ->(a d > c b) son distintos, devuelve 1
	//Si ->(a d < c b) son distintos, devuelve -1
    @Override
    public int compareTo(Rational c){
        if ((this.num * c.den) > (this.den * c.num)){
            return 1;
        }
        
        if ((this.num * c.den) < (this.den * c.num)){
            return -1;
        }
        
        return 0;
    }
    
    // Método clonar
    @Override
    public Rational clone(){
        int numerator = this.num;
        int denominator = this.den;
        return new Rational(numerator, denominator);
    }
}
}

##Ejercicio Cuenta :
{
public class Cuenta {
	//Atributos
    private String titular;
    private double cantidad;
    
	
	//Constructor
    public Cuenta(String titular, double cantidad) {
        this.titular = titular;
        //Si la cantidad es menor que cero, lo ponemos a cero
        if (cantidad < 0) {
            this.cantidad = 0;
        } else {
            this.cantidad = cantidad;
        }
    }
    
    public String getTitular() {
        return titular;
    }
 
    public void setTitular(String titular) {
        this.titular = titular;
    }
 
    public double getCantidad() {
        return cantidad;
    }
 
    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
 
    /*
     Ingresa dinero en la cuenta, 
     solo si es positivo la cantidad
     */
    public void ingresar(double cantidad) {
        if(cantidad > 0){
            this.cantidad += cantidad;   
        }
    }
 
    /*
     Retira una cantidad en la cuenta, si se quedara en negativo se quedaria
     en cero
     */
    public void retirar(double cantidad) {
        if (this.cantidad - cantidad < 0) {
            this.cantidad = 0;
        } else {
            this.cantidad -= cantidad;
        }
    }
 
    /*
    Devuelve el estado del objeto
     */
    @Override
    public String toString() {
        return "El titular " + titular + " tiene " + cantidad + " euros en la cuenta";
    }
 
}
}

##Ejercicio Personas :
{
	import java.util.*;

public class Persona {
    public String nombre = "";
    private int edad = 0;
    private String DNI;
    private char sexo = ' ';
    private double peso = 0.;
    private double altura = 0.;
    
    public Persona(String nombre,int edad, char sexo,double peso, double altura){
        this.nombre=nombre;
        this.edad=edad;
        this.DNI=generaDNI();
        this.sexo=sexo;
        this.peso=peso;
        this.altura=altura;
    }
    public Persona(String nombre,int edad,char sexo){
        this.nombre=nombre;
        this.edad=edad;
        this.sexo=sexo;
        this.DNI=generaDNI();
    }
    public Persona(){
        this.DNI=generaDNI();
    }
    
    //Devuelve el IMC
    public double CalcularIMC(){
        return Math.floor(((this.peso/(this.altura*this.altura))*100)/100);
    }
    
    //Devuelve un booleano (True si es mayor de edad, False si es menor de edad)
    public boolean esMayorDeEdad(){
        if(this.edad>=18)return true;
        else return false;
    }
    
    public char comprobarSexo(){
        return this.sexo;
    }
    
    @Override
    public String toString(){
        String res = "";
        //nombre
        if(nombre.equals(""))res+="nombre: NO DATA, ";
        else res+="Nombre: "+nombre+", ";
        //edad
        if(edad<=0)res+="edad: NO DATA, ";
        else res+="Edad: "+edad+", ";
        //dni
        res+="DNI: "+DNI+", ";
        //sexo
        if(sexo=='M')res+="sexo: Mujer, ";
        if(sexo=='H')res+="sexo: Hombre, ";
        if(sexo!='H'&&sexo!='M') res+="sexo: NO DATA, ";
        //peso
        if(peso==0.)res+="peso: NO DATA, ";
        else res+="peso: "+peso+", ";
        //altura
        if(altura==0.)res+="altura: NO DATA, ";
        else res+="altura: "+altura+".";
        return res;
    }
    
    public static String generaDNI(){
        String res ="";
        Random r = new Random();
        String alfabeto="ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        res = Integer.toString((int)(Math.floor(Math.random()*100000000+1)));
        res += alfabeto.charAt(r.nextInt(alfabeto.length()));
        return res;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }
    public void setSexo(char sexo){
        this.sexo=sexo;
    }
    public void setAltura(double altura){
        this.altura=altura;
    }
    public void setPeso(double peso){
        this.peso=peso;
    }
    
}
}
}

#ESTRUCTURAS ENCADENADAS
{
#Ejercicio ListOfInt : 
{
	#Referencias al nodo (Lo dan en los ejercicios)
{
private class Node {
    int value; //Valor que es Número
    Node next; //Nodo que apunta al siguiente Nodo
}
    
	#Atributos de la ListOfInt
private Node data;     // Referencia al primer nodo de la lista
rivate int size = 0;  // Número de nodos en la lista
        
	
public int getSize () {
    return size;
}
}

	#Insertar un elemento en la lista (Lo dan en los ejercicios)
public void insert(int value) {
    Node prev = null;
    Node current = data;
        
    // Busca la posición donde debe ir value
    while (current != null && current.value < value) {
        prev = current;
        current = current.next;
    }
        
	// Crea un nodo con el nuevo valor
    Node newNode = new Node();
    newNode.value = value;
    newNode.next = current;
        
    // Se diferencia el caso particular de insertar al principio
    if(prev == null)data = newNode;
    else prev.next = newNode;
    size++;
}

	#Borra un elemento de una lista(Lo dan en los ejercicios)
public void deleteAt(int pos) {
    Node prev = null;
    Node current = data;
    int auxPos = 0; 
    // Se avanza hasta la posición del elemento a borrar 
    while (auxPos < pos) {
        // El elemento actual es el previo al siguiente
        prev = current;
        current = current.next;
        auxPos++;
    }     
    // Se diferencia el caso particular de borrar el primer elemento
    if (pos == 0) {
        data = data.next;
    }
    else {
        prev.next = current.next;
    }
    size--;
}

	#Busca un valor en una lista(Lo dan en los ejercicios)
public boolean contains(int value) {
    Node aux = data;
        
	// Busca la posición donde debe ir value
    while (aux != null && aux.value < value) {
        aux = aux.next;
    }
        
    /*Si el bucle se ha detenido antes del final
    puede haberlo hecho en el valor buscado o 
    en uno mayor*/
    if (aux != null) {
        return aux.value == value;
    } else {
        return false;
    }
}

	#ValueAt devuelve el valor del parámetro asignado
public int valueAt(int a){
    if(a<0 || a>=getSize())return -1;
    int pos = 0;
    Node aux = data;
    while (aux != null) {
        if(pos==a)return aux.value;
        pos++;
        aux = aux.next;
    }
    return -1;
}

	#indexOf devuelve la posicion donde se encuentra por primera vez un valor pasado como parámetro, si no lo encuentra devuelve -1
public int indexOf(int a){
    int pos=0;
    Node aux = data;
        
    for(int i = 0; i<size;i++){
		if(aux.value==a)return pos;
        else{
		pos++;  
        aux = aux.next;
        }
    }
    return -1;
}
	
	#Convierte un ListOfInt en una String con el formato ()>x
@Override
public String toString(){
    String res = "";
    Node aux = data;
    while(aux != null){
        res += "(" + aux.value +")"+ ">";
        aux = aux.next;
    }
    return res + "x";
}

	# Clona la ListOfInt
public ListOfInt NuevaLista(ListOfInt l){
    ListOfInt res = new ListOfInt();
    res.size=this.size;
    Node aux = this.data;
    for(int i = 0; i <= l.getSize()-1; i++) {
        res.insert(aux.value);
        aux = aux.next;
    }
    return res;
}
	
}

#QueuOfInt :
{
	#Referencias al Node (Lo da en el ejercicio)
{
protected class Node { // Nodos que almacenan los elementos de la cola
    int info;        // Información almacenada en el nodo
    Node next;       // Encadenamiento al siguiente nodo 
}

protected Node front = null; // Referencia al primer elemento
protected Node rear = null;  // Referencia al último elemento
}

	#Método Insert (Lo dan en el ejercicio)
public void insert(int element) {
    if (rear == null) { // La cola está vacía
        rear = new Node(); // se crea un nodo y rear y front
        front = rear;      // apuntan a él, que es el único
    } else { //La cola no está vacía 
        rear.next = new Node(); // se crea un nuevo nodo que
        rear = rear.next;       // se engancha al final y se
    }                           // actualiza rear
    rear.info = element; // se almacena el elemento en el nuevo nodo
}

	#Retorna el Primer elemento de la lista (Lo dan en el ejercicio)
public int getFirst() {
    return front.info; // ¡¡ Da error si la cola está vacía !!
}

	#Elimina el primer elemento de la cola
public void remove() {
    if (front != null) { // La cola no está vacía
        front = front.next; // El segundo elemento pasa al frente
            
        if (front == null) { // Solo había un nodo, la cola
            rear = null;     // ha quedado vacía
        }
    }
}

#ESTA ES LA PARTE DEL EJERCICIO
###ExtendedQueuOfInt :

	#CompareTo
	//Compara las length de las QueuOfInt, y su primer valor. 
@Override
public int compareTo(QueueOfInt arg0) {
    Node aux = front;
    Node aux2 = arg0.front;
        
    if (aux != null && aux2 != null){		 //Compara su primer valor
        if (aux.info > aux2.info) return -1; //->Si aux es mayor que aux2 return -1.
		else if (aux.info < aux2.info) return 1;//->Si aux es menor que aux2 return 1.
        }
        
    while (aux != null || aux2 != null){
        if (aux == null && aux2 != null) return 1;
        else if (aux != null && aux2 == null) return -1;
            
            aux = aux.next;
            aux2 = aux2.next;
        }
        return 0;
    }
	
	#Equals 
	//(Devuelve true si las 2 QueuOfInt  son iguales valor por valor)
@Override
public boolean equals(Object arg0) {
    QueueOfInt arg = (QueueOfInt) arg0;
    Node aux = front;
    Node aux2 = arg.front;
    while (aux != null && aux2 != null){
        if (aux.info != aux2.info){
            return false;
        }
            
        aux = aux.next;
        aux2 = aux2.next;
    }
    if (aux == null && aux2 == null){
        return true;
    }
    return false;
}
	
	#ToString
@Override
public String toString() {
    String res = "<";
    Node aux = front;
    while (aux != null){
        res += aux.info;
        if (aux.next != null){
            res += "-";
        }
        ux = aux.next;
    }
    return res + "<";
}	

	#Clone
@Override
    public ExtendedQueueOfInt clone() {
        ExtendedQueueOfInt clon = new ExtendedQueueOfInt();
        Node aux = front;
        while (aux != null){
            clon.insert(aux.info);
            aux = aux.next;
        }
        return clon;
    }

}

#Lista Encadenada :
{
public class ListaEncadenada {

    // Clase privada para formar la lista
	private class Nodo {
		int info;
		Nodo sig;		
		
		// Constructor de un Nodo
		public Nodo(int valor) {
			info = valor;
			sig = null;
		}
	}
	
	// El atributo de la clase ListaEncadenada almacena la dirección del
	// primer Nodo de la lista
	Nodo primero;
	
	// Constructor
	public ListaEncadenada() {
	    // No es estrictamente necesario ya que Java lo inicializa a null
		primero = null;
	}

    // Inserta un Nodo por el principio de la lista
	public void insertar(int valor) {
	    // Se crea un nodo para añadir a la lista
		Nodo bloque = new Nodo(valor);
		if (primero == null) { // ¿La lista está vacía?
			primero = bloque;
		} else {
		    // El nuevo nodo se coloca como primero de la lista
			bloque.sig = primero;
			primero = bloque;
		}
		
	}
	
	// Inserta un Nodo al final de la lista encadenada
	public void insertarFinal(int valor) {
		if (primero == null) {// ¿La lista está vacía?
			primero = new Nodo(valor);			
		} else {
			Nodo aux = primero;
			while (aux.sig != null) { // Se avanza por la lista hasta llegar 
			                          // al último nodo
				aux = aux.sig;
			}
			aux.sig = new Nodo(valor); // Se encadena el nuevo Nodo al final
		}
	}
	
	// En una lista ordenada
	// Insertar, ordenadamente de menor a mayor, un nuevo elemento en la lista
	public void insertarOrden(int valor) {
		if (primero == null) { //¿La lista está vacía?
			primero = new Nodo(valor);
		} else {
			if (primero.info > valor) { // ¿El nuevo nodo es el que va al primero?
				Nodo aux = new Nodo(valor);
				aux.sig = primero;
				primero = aux;
			} else {
			    // Se recorre la lista para descubrir donde se inserta
				Nodo aux = primero;
				while (aux.sig != null && aux.sig.info < valor) {
					aux = aux.sig;
				}
				Nodo nuevo = new Nodo(valor); // Se encadena correctamente el Nodo
				nuevo.sig = aux.sig;
				aux.sig = nuevo;
			}
		}
	}
	
	// Elimina de la lista el Nodo cuyo campo 'info ' sea igual al parámetro valor
	public void eliminarNodo(int valor) {
		if (primero != null) { // ¿Se elimina el primero de la lista?
			if (primero.info == valor) {
				primero = primero.sig;
			} else {
				Nodo aux = primero;
				while (aux.sig != null && aux.sig.info != valor) {
					aux = aux.sig; // Se avanza por la lista buscando 'valor'
				}	
				if (aux.sig != null) { // ¿Se encontró el elemento 'valor'?
					aux.sig = aux.sig.sig;
				}
			}
		}
	}

	// Cuenta el número de Nodos que hay en la lista
	public int count() {
		int res = 0;
		Nodo aux = primero;
		while (aux != null) {
			res ++;
			aux = aux.sig;
		}
		return res;
	}
	
	// Devuelve una String con los valores almacenados en la lista separados
	// por un espacio.
	@Override
	public String toString() {
		String res = "";
		Nodo aux = primero;
		while (aux != null) {
			res += aux.info + " ";
			aux = aux.sig;
		}
		return res.trim();
	}
	
	// Desarrollar el método borrar Pares.
	// Este método eliminará de la lista todos los nodos que almacenan un número par
	// Desarrolle este método usando el método 'eliminarNodo'.
	public void borrarPares1(){
	    // Ponga aquí su código
            Nodo aux=primero;
            int res = 0;
            while(aux!=null){
                res=aux.info%2;
                if(res==0){
                    eliminarNodo(aux.info);
                }
                aux=aux.sig;
            }
	}

	// Desarrolle este método sin usar el método 'eliminarNodo'.
	public void borrarPares2(){
	    Nodo aux=primero;
            int res = 0;
            while(aux!=null){
                res=aux.info%2;
                if(res==0){
                    if (primero != null) { // ¿Se elimina el primero de la lista?
			if (primero.info == aux.info) {
				primero = primero.sig;
			} else {
				Nodo bloque = primero;
				while (bloque.sig != null && bloque.sig.info != aux.info) {
					bloque = bloque.sig; // Se avanza por la lista buscando 'valor'
				}	
				if (bloque.sig != null) { // ¿Se encontró el elemento 'valor'?
					bloque.sig = bloque.sig.sig;
				}
			}
		}
                }
                aux=aux.sig;
            }
            
	}
	
	// Desarrolle el método invertirLista.
	// Este método le dará la vuelta a todos los nodos de la lista.
	// Ejemplo: Si la lista contiene la secuencia de valores 3 -> 5 -> 2 -> 8 ->/
	// La lista quedará: 8 -> 2 -> 5 -> 3 -> /
	// No se han de crear nuevos Nodos.
	// No se puede usar ningún otro método.
	public void invertirLista() {
            int[] res = new int[count()];
            Nodo aux=primero;
            for(int i=(count()-1);i>=0;i--){
                res[i]=aux.info;
                aux=aux.sig;
            }
            for(int i=0;i<=res.length-1;i++){
                if (primero != null) { // ¿Se elimina el primero de la lista?
			if (primero.info == res[i]) {
				primero = primero.sig;
			} else {
				Nodo bloque = primero;
				while (bloque.sig != null && bloque.sig.info != res[i]) {
					bloque = bloque.sig; // Se avanza por la lista buscando 'valor'
				}	
				if (bloque.sig != null) { // ¿Se encontró el elemento 'valor'?
					bloque.sig = bloque.sig.sig;
				}
			}
		}
                if (primero == null) {// ¿La lista está vacía?
			primero = new Nodo(res[i]);			
		} else {
			Nodo bloque = primero;
			while (bloque.sig != null) { // Se avanza por la lista hasta llegar 
			                          // al último nodo
				bloque = bloque.sig;
			}
			bloque.sig = new Nodo(res[i]); // Se encadena el nuevo Nodo al final
		}
            }
	    // Ponga aquí su código
            //eliminarNodo(res[i]);
            //insertarFinal(res[i]);
            
	}
	
	// El siguiente método devolverá una nueva lista encadenada que será la 
	// resultante de unir dos listas encadenadas que se encuentran ordenadas
	// de menor a mayor. La nueva lista también quedará ordenada de menor a mayor
	// Las listas originales quedarán inalteradas.
	// No se puede apoyar en ningún otro método.
	public ListaEncadenada mezcla(ListaEncadenada lista2) {
	    int[] res = new int[count()+lista2.count()];
            Nodo aux=primero;
            Nodo aux2=lista2.primero;
            
            for(int i = 0; i<=count()-1; i++){
                res[i]=aux.info;
                aux=aux.sig;
            }
            for(int i = count(); i<=count()-1+lista2.count(); i++){
                res[i]=aux2.info;
                aux2=aux2.sig;
            }
            ListaEncadenada resultado = new ListaEncadenada();
            for(int i =0;i<=res.length-1;i++){
               if (resultado.primero == null) {// ¿La lista está vacía?
			resultado.primero = new Nodo(res[i]);			
		} else {
			Nodo bloque = resultado.primero;
			while (bloque.sig != null) { // Se avanza por la lista hasta llegar 
			                          // al último nodo
				bloque = bloque.sig;
			}
			bloque.sig = new Nodo(res[i]); // Se encadena el nuevo Nodo al final
		}
            }
            return resultado;
	}
}

}
}

#LEER Y ESCRIBIR - Ficheros
{
#Leer 
{
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
public static void leer(String nombrefichero) {
    try {
    	File fich = new File(nombrefichero);
	    Scanner s = new Scanner(fich);
	    String line;
	    while (s.hasNextLine()) {
	        line = s.nextLine();
	        System.out.println(line);
	    }
	    s.close();
	} 
	catch (IOException e) {
		System.out.println(e.getMessage());
    };		
}
}
	
#Escribir
{
import java.io.PrintWriter;
public static void escribir(String nombrefichero) {
	try {
		PrintWriter pw = new PrintWriter(nombrefichero);
		for(int i = 10; i < 20; i++) {
			pw.write("i = " + i);		
			pw.write("\r\n"); // Se añade el salto de línea
			pw.println();
		}
		pw.close();
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
}
}

##Leer y escribir implementados en un ejercicio

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.*;

#Leer
public static void leer(String nombrefichero,ListaEncadenada res) {
    try {   
		File fich = new File(nombrefichero);
	    Scanner s = new Scanner(fich);
	    String line, total="";
	    while (s.hasNextLine()) {
	        line = s.nextLine();
	        total +=" "+line; //Aqui se modifica para juntar todo en una sola string.
	    }
	    total = total.trim();	//Estos cambios son para el desarrollo del problema.
	    String[] partes=total.split(" ");
	    ordenarOut(res,partes);	//Llamamos a un metodo que ordena los digitos.
	    s.close();
    } 
    catch (IOException e) {
		System.out.println(e.getMessage());
    };		
}

#Escribir
public static void escribir(String nombrefichero, ListaEncadenada res) {
	try {
		PrintWriter pw = new PrintWriter(nombrefichero);
		pw.print(res.toString());
		pw.close();
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
} 
}

#Pasar de una variable a otra:
{
	#String a int
	int n = Integer.parseInt(String s1);
	
	#Int a String
	String s1 = String.valueOf(int n);
	
	#Char a String
	String s1 = Character.toString(char c);
	
	#String a char
	char c = cadena.charAt(0);
	
	#String a double
	double n = Double.parseDouble(String s1);
	
	#Double a String
	String S1 = String.valueOf(double n);
	
	#String a float(float es como double)
	float f = Float.parseFloat(String s1);
	
	#Float a String
	String s1 = Float.toString(900.1f);
	
	#String a Boolean
	Boolean boolean = Boolean.valueOf("true");
	// o
	boolean boolean = Boolean.parseBoolean("false");
	
	#Boolean a String
	boolean b = true;
	String cadena = String.valueOf(b);
	// o
	String cadena = Boolean.toString(b);	
}
