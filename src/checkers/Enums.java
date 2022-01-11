package checkers;

public class Enums {
	public enum BoardSetups {
		STANDARD("standard"),
		TEST("test"),
		JUMPING1("jumping1"),
		JUMPING2("jumping2"),
		KINGJUMP("kingjump"),
		MULTIPLEJUMPS1("multiplejumps1"),
		MULTIPLEJUMPS2("multiplejumps2"),
		MULTIPLEJUMPSAGAINSTEDGE("multiplejumpsagainstedge"),
		BLACKATTACKEDGE("blackattackedge"),
		KINGDIRECTIONALATTACK("kingdirectionalattack"),
		CS50DEMO("cs50demo");
		
		private String setup;
		
		private BoardSetups(String setup) {
			this.setup = setup;
		}
		
		@Override
		public String toString() {
			return this.setup;
		}
	}

	public enum Modifier {
		UPLEFT {
			@Override
			public int[] get() {
				return new int[] { 1, -1 };
			}
		},
		UPRIGHT {
			@Override
			public int[] get() {
				return new int[] { 1, 1 };
			}
		},
		DOWNRIGHT {
			@Override
			public int[] get() {
				return new int[] { -1, 1 };
			}
		},
		DOWNLEFT {
			@Override
			public int[] get() {
				return new int[] { -1, -1 };
			}
		};

		public int[] get() { // almost like an abstract method, overrider above inside the Modifier initialiser(?)
			return null;
		}

		
	}
	
	public static Modifier[] getModifiers() {
		return new Modifier[] { Modifier.UPLEFT, Modifier.UPRIGHT, Modifier.DOWNRIGHT, Modifier.DOWNLEFT };
	}
}
