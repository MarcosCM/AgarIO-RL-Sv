# AgarIO in Real Life

## Tecnologías Servidor:

- Spring Framework
- MongoDB
- JSON-HATEOAS

***El Cliente esta disponible en este enlace: *** https://github.com/JorgeCoke/AgarIO-RL-Cl

## Despliegue Openshift

El servidor esta preparado para desplegarlo en OpenShift, aunque tambien se puede desplegar en local

### Paso 1: Crear aplicacion DIY

    rhc app create ServerAgarIORL diy-0.1

### Paso 2: Añadir MongoDB a la aplicacion

    rhc cartridge add mongodb-2.4 --app ServerAgarIORL

Podemos ver el estado con el siguiente comando:

    rhc cartridge status mongodb-2.4 --app ServerAgarIORL

### Paso 3: Borramos el código fuente

    git rm -rf .openshift README.md diy misc

Hacemos commit:

    git commit -am "Borrado codigo inicial"

### Paso 4: Push del nuevo código

    git remote add upstream https://github.com/JorgeCoke/AgarIO-RL-Sv.git
    git pull -s recursive -X theirs upstream master
    git push

Una vez subidos los cambios podemos ver el servidor desde la URL: http://ServerAgarIORL-NOMBREUSUARIO.rhcloud.com/

Para ver los logs:

    rhc <app-name> tail
