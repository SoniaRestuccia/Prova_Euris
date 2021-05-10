package org.generation.italy.monete.util;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/*
 * File creato durante il corso 
 */

public interface IMappablePro {

	// Questo metodo permetterà la conversione automatica da oggetto a mappa
	// Tutte le classi che implementeranno quest'interfaccia avranno questo
	// metodo già implementato
	default Map<String, String> toMap() {
		Map<String, String> ris = new HashMap<>();

		// Devo venire a conoscenza della classe dell'oggetto su cui viene
		// invocato questo metodo
		// Class è un Generics. non gli specifico il tipo.
		// ? extends IMappablePro significa che questa classe potrà essere
		// di un qualsiasi tipo che estende IMappablePro
		// Gli sto dicendo che estendendo IMappablePro mi garantirà un'uniformità
		// base di implementazione
		Class<? extends IMappablePro> classe = getClass();

		// Una volta che ho recuperato la classe dell'oggetto sono in grado
		// di risalire a tutto ciò che le appartiene
		// Dalla classe recupero un vettore di metodi contenente tutti i metodi
		// che le appartengono
		Method[] metodi = classe.getMethods();

		for (Method metodo : metodi) {
			// Mi serve il nome del metodo. Lo registro in una stringa
			String nomeMetodo = metodo.getName();

			// Mi interessano solamente i getters
			if (!nomeMetodo.equalsIgnoreCase("getclass")
					&& (nomeMetodo.startsWith("get") || nomeMetodo.startsWith("is"))) {

				try {

					Object v = metodo.invoke(this);

					String valore = v == null ? "" : v.toString();

					int indiceDiPartenza = nomeMetodo.startsWith("get") ? 3 : 2;

					String chiave = nomeMetodo.substring(indiceDiPartenza).toLowerCase();

					ris.put(chiave, valore);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		ris.put("Class", classe.getSimpleName());

		return ris;
	}

	// Da mappa ad oggetto
	default void fromMap(Map<String, String> map) {

		Class<? extends IMappablePro> thisClass = getClass();

		Method[] metodi = thisClass.getMethods();

		for (Method metodo : metodi) {

			String nomeMetodo = metodo.getName();

			if (nomeMetodo.startsWith("set")) {

				String chiave = nomeMetodo.substring(3).toLowerCase();

				String valore = map.get(chiave);

				Class<?> tipoParametro = metodo.getParameterTypes()[0];

				try {
					if (tipoParametro.equals(boolean.class)) {
						metodo.invoke(this, valore.equals("1") || valore.equals("true"));
					} else if (tipoParametro.equals(char.class)) {

						metodo.invoke(this, valore.charAt(0));
					} else if (tipoParametro.isPrimitive()) {

						Class<?> tipoBoxato = Array.get(Array.newInstance(tipoParametro, 1), 0).getClass();

						Method[] metodiBoxati = tipoBoxato.getMethods();

						for (Method metodoBoxato : metodiBoxati) {

							if (metodoBoxato.getName().startsWith("parse") && metodoBoxato.getParameterCount() == 1) {

								metodo.invoke(this, metodoBoxato.invoke(null, valore));
								break;
							}
						}
					} else {
						metodo.invoke(this, valore);
					}

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}

			}
		}
	}

	static <T extends IMappablePro> T fromMap(Class<T> type, Map<String, String> map) {
		T ris = null;

		try {
			Constructor<T> constructor = type.getConstructor();
			ris = constructor.newInstance();
			ris.fromMap(map);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {

			System.err.println("Manca il costruttore senza parametri, impossibile istanziare l'oggetto");
		}

		return ris;
	}

}