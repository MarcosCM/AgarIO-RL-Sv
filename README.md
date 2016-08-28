![alt tag](https://raw.githubusercontent.com/JorgeCoke/GeoMe-Cliente/master/app/src/main/res/mipmap-xxhdpi/ic_launcher.png)

# Proyecto GeoMe

GeoMe es un proyecto basado en una aplicación para dispositivos móviles Android cuya finalidad principal es prevenir accidentes, enviando alertas en tiempo real al usuario basándose en la posición geográfica proporcionada por los propios usuarios cercanos al cliente. 

Además de generar alertas, la aplicación muestra en tiempo real la ubicación de los usuarios alrededor de la ubicación del cliente en un mapa ofrecido por el servicio de Google Maps.

## Tecnologías Servidor:

- Spring Framework
- MognoDB
- JSON-HATEOAS

***El Cliente esta disponible en este enlace: *** https://github.com/JorgeCoke/GeoMe-Cliente

## Despliegue Openshift

El servidor esta preparado para desplegarlo en OpenShift, aunque tambien se puede desplegar en local

### Paso 1: Crear aplicacion DIY

    rhc app create ServerGeome diy-0.1

### Paso 2: Añadir MongoDB a la aplicacion

    rhc cartridge add mongodb-2.4 --app ServerGeome

Podemos ver el estado con el siguiente comando:

    rhc cartridge status mongodb-2.4 --app ServerGeome

### Paso 3: Borramos el código fuente

    git rm -rf .openshift README.md diy misc

Hacemos commit:

    git commit -am "Borrado codigo inicial"

### Paso 4: Push del nuevo código

    git remote add upstream https://github.com/JorgeCoke/GeoMe-Servidor.git
    git pull -s recursive -X theirs upstream master
    git push

### Paso 5: Comprobar funcionamiento

Una vez subidos los cambios podemos ver el estado del servidor desde la URL: http://ServerGeome-NOMBREUSUARIO.rhcloud.com/manage/health

	{
		"status": "UP",
		"database": "MongoDB",
		"hello": 1
	}
