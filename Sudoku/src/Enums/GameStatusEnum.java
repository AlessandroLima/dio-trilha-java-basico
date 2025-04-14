package Enums;

public enum GameStatusEnum {
	NON_STARTED("Não iniciado"),
	INCOMPLETE("Em progresso"),
	COMPLETE("Completo");
	
	private String label;

	private GameStatusEnum(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}	
	
}
