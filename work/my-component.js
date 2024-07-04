// my-component.js
import { ref, watchEffect } from 'vue'
export default {
    setup() {
        const deck = ref(null)

        watchEffect(async () => {
            // this effect will run immediately and then
            // re-run whenever currentBranch.value changes
            const url = `http://localhost:8080/v1/deck/1`
            deck.value = await (await fetch(url)).json()
        })

        return { deck }
    }
}