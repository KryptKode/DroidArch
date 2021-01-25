package com.kryptkode.basemvi

typealias Reducer<State, Action> = (previousState: State, action: Action) -> State
