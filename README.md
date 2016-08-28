# Despliegue Openshift

El servidor esta preparado para desplegarlo en OpenShift.

# Paso 1: Crear aplicacion DIY

    rhc app create ServerGeome diy-0.1

# Paso 2: Añadir MongoDB a la aplicacion

  rhc cartridge add mongodb-2.4 --app ServerGeome

Podemos ver el estado con el siguiente comando:

  rhc cartridge status mongodb-2.4 --app ServerGeome

# Paso 3: Borramos el código fuente

    git rm -rf .openshift README.md diy misc

Hacemos commit:

    git commit -am "Borrado codigo inicial"

# Paso 4: Push del nuevo código

    git remote add upstream <URL de GIT>
    git pull -s recursive -X theirs upstream master
    git push

# Paso 5: Comprobar funcionamiento

Una vez subidos los cambios podemos ver el estado del servidor desde la URL: http://ServerGeome-<namespace>.rhcloud.com/manage/health

	{
		"status": "UP",
		"database": "MongoDB",
		"hello": 1
	}
