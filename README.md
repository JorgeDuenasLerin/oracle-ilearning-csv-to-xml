# oracle-ilearning-csv-to-xml
Comando para generar XML con usuarios de la plataforma Oracle iLearning

# Ejemplo de ejecución

Sin parámetros
```
[folen:~/Desktop/ilearning] $ java -jar csv-to-xml-ilearning.jar
Error en parámetros
Missing required options: d, y, p, t
usage:
 -d,--datos <datos>           Ruta al fichero de datos
 -e,--extendido <extendido>   Todos los cursos! y profesor!!
 -p,--pass <pass>             Contraseña por defecto
 -t,--tutor <tutor>           Usuario tutor en plataforma
                              Para el JDLC: ies_juan_de_la_cierva
 -y,--año <año>               Prefijo para gestionar varios años de
                              usuarios

```

Ejemplo del fichero de profes:
```
java -jar csv-to-xml-ilearning.jar -d data/profes.csv -p secreto -t ies_juan_de_la_cierva -y 19_20 > out/prof.xml
```

Ejemplo fichero de DAW1V
```
java -jar csv-to-xml-ilearning.jar -d data/daw1v.csv -p secreto -t ies_juan_de_la_cierva -y 19_20 > out/daw1v.xml
```

# Formato fichero datos
El formato del fichero de datos es el siguiente. Debe incluir cabecera

```
Curso;DNI;Apellidos;Nombre;Email Address
PROF;0;G***z;L**a;i****a@gmail.com
PROF;0;P***z;I**o;j***5@gmail.com
```

Los usuarios creados serán:
19_20_PROF_<email>

``` <PREFIJO>_<UNIDAD_ORGANIZATIVA_DEL_CSV>_<EMAIL> ```

De esta forma se pueden hacer filtros por año y clase
