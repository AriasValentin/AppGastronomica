package Model.Clases;

/**
 * Representan los posibles lugares de consumo.
 */
public enum LugarConsumo {
        MesaNro1,MesaNro2,MesaNro3,MesaNro4,BARRA,TakeAway;
        boolean disponible; // true si esta disponible para consumir.

        private LugarConsumo()
        {
            this.disponible = true;
        }
}
