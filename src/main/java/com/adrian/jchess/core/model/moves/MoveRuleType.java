package com.adrian.jchess.core.model.moves;

/**
 * Enum holding how many times a move rule can be applied on a specific turn.
 */
public enum MoveRuleType {
	UNLIMITED, // can be applied as long as the board dimension limits allow it or until a own
				// piece is in the way
	OCCASIONAL, // can only be applied if specific conditions are met (like casteling/en-passant
				// or moving the pawn 2 squares on its first move)
	ONCE // specific move rule can only be applied once
}
