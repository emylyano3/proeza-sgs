package com.proeza.core.classmapper.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion utilizada para indicar que el atributo de un objeto mapeable debe ser mapeado con uno especifico de otro
 * objeto que es del tipo coleccion.
 * 
 * @author c.eschia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
@Documented
public @interface CollectionBind {
	/**
	 * Indica la referencia del mapping al que pertenece. Un objeto puede tener varios mappings definidos, la forma de
	 * indicar que un bind pertenece a uno y no otro mapping es a traves de esta propiedad de la anotacion.
	 * 
	 * @return El nombre del mapping al que pertenece
	 * @see Mapping
	 */
	String mappingName() default "";

	/**
	 * Indica con que atributo del otro objeto (el indicado en la definicion del mapping) debe mapearse ek atributo
	 * anotado con esta anotacion.
	 * 
	 * @return El nombre del campo enlazado
	 */
	String field() default "";

	/**
	 * Indica el tipo de dato que acepta la coleccion destino, es decir la coleccion a la cual se esta bindeando el
	 * campo
	 * 
	 * @return El tipo de coleccion del atributo enlazado
	 */
	Class<?> type();

	/**
	 * Indica desde que atributo del campo anotado debe mapearse el atributo enlazado
	 * 
	 * @return La ruta al atributo fuente
	 * @see Mapping
	 */
	String source() default "";

	/**
	 * Se utiliza solo en el mapeo inverso, es decir cuando se mapea desde el objeto enlazado hacia el objeto mapeado,
	 * donde el objeto mapeado es quien tiene declarado el mapeo (<tt>@Mapping</tt>) y el objeto enladazo es quien es
	 * apuntado por la definicion del bindeo.<br>
	 * 
	 * Es util en los casos en los que se define un bindeo indirecto.
	 * <p>
	 * <b>Ejemplo:</b>
	 * 
	 * <pre>
	 * &#064;Mapping(type = PersonaVO.class)
	 * class Persona {
	 * 	&#064;Bind(source = &quot;nombre&quot;, field = &quot;nombreMascota&quot;)
	 * 	Mascota	mascota;
	 * 	&#064;Bind
	 * 	String	nombre;
	 * }
	 * 
	 * class Mascota {
	 * 	String	nombre;
	 * 	String	tipo;
	 * 	String	raza;
	 * }
	 * 
	 * class PersonaVO {
	 * 	String	nombre;
	 * 	String	nombreMascota;
	 * }
	 * </pre>
	 * 
	 * Se definio que el campo <tt>Persona.mascota.nombre</tt> se enlace con el campo <tt>PersonaVO.nombreMascota</tt>.<br>
	 * El mapeo "hacia adelante", es factible: <tt>Persona.mascota.nombre</tt> > <tt>PersonaVO.nombreMascota</tt> <br>
	 * Pero cuando el bindeo es al reves, no siempre alcanza con esta definicion.
	 * <p>
	 * <b>Pensar en el siguiente caso:</b> Se trae desde una fuente de datos la entidad <tt>Persona</tt> y se mapea a un
	 * <tt>PersonaVO</tt>. En <tt>PersonaVO</tt> se modifica el atributo <tt>nombreMascota</tt>.
	 * <p>
	 * <b>Esto puede significar dos cosas:</b>
	 * <ol>
	 * <li>Se quiso cambiar el nombre de la mascota de la persona</li>
	 * <li>Se quiso cambiar la mascota</li>
	 * </ol>
	 * Estas dos opciones son muy diferentes.<br>
	 * <ol>
	 * <li>La primera dice que al mapear inversamente, <tt>Persona.mascota.nombre</tt> deber�a cambiarse por el valor de
	 * <tt>PersonaVo.nombreMascota</tt>.</li>
	 * <li>La segunda dice que al mapear inversamente, <tt>Persona.mascota</tt> deber�a cambiarse por la mascota
	 * identificada por <tt>PersonaVo.nombreMascota</tt>.</li>
	 * </ol>
	 * La semantica de ese mapeo puede ser particular para cada caso. Para resolver esa problematica se usa al campo
	 * <tt>backwardBinder</tt>.
	 * 
	 * <pre>
	 * &#064;Mapping(type = PersonaVO.class)
	 * class Persona {
	 * 	&#064;Bind(source = &quot;nombre&quot;, field = &quot;nombreMascota&quot;, <b>backwardBinder="bindMascota"</b>)
	 * 	Mascota	mascota;
	 * 	&#064;Bind
	 * 	String	nombre;
	 * 
	 *  void bindMascota(String nombre) {
	 *    this.mascota = mascotaRepository.findByName(nombre);
	 *  }
	 * }
	 * </pre>
	 */
	String backwardBinder() default "";
}
