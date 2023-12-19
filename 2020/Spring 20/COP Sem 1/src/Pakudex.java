public class Pakudex {

    private int capacity, size;
    private String[] pakudex;
    private Pakuri[] Deck;
    private int[] stats = new int[3];

    public Pakudex() {
        capacity = 20;
    }

    public Pakudex(int capacity) {
        this.capacity = capacity;
        pakudex = new String[capacity];
        Deck = new Pakuri[capacity];
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public String[] getSpeciesArray() {
        if (pakudex[0] == null) {
            return null;
        }
        return pakudex;
    }

    public int[] getStats(String species) {
        for (int i = 0; i < getCapacity(); i++) {
            if (pakudex[i].equals(species)) {
                stats[0] = Deck[i].getAttack();
                stats[1] = Deck[i].getDefense();
                stats[2] = Deck[i].getSpeed();
                return stats;
            }
        }
        return null;
    }

    public void sortPakuri() {
        if (size > 1) {             //Only sorts lists greater than 1 Pakuri in size to avoid nullPointerException
            for (int i = 0; i < size - 1; ++i) {
                for (int j = 1; j < size; ++j) {
                    if (pakudex[i].compareTo(pakudex[j]) > 0) {
                        //Sort string deck
                        String temp = pakudex[i];
                        pakudex[i] = pakudex[j];
                        pakudex[j] = temp;
                        //Sort Pakuri Deck
                        Pakuri temp1 = Deck[i];
                        Deck[i] = Deck[j];
                        Deck[j] = temp1;
                    }
                }
            }
        }
    }

    public boolean addPakuri(String species) {
        for (int i = 0; i < pakudex.length; i++) {
            if (pakudex[i] == null) {
                pakudex[i] = species;
                Deck[i] = new Pakuri(species);
                size++;
                return true;
            } else if (pakudex[i].equals(species)) {
                break;
            }
        }
        return false;
    }

    public boolean evolveSpecies(String species) {
        for (Pakuri pakuri : Deck) {
            if (pakuri.getSpecies().equals(species)) {
                pakuri.evolve();
                return true;
            }
        }
        return false;
    }
}