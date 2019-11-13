<template>
    <div id="app">
        <div class="container-main">
            <nav-bar></nav-bar>
            <transition name="page">
                <router-view></router-view>
            </transition>
            <less-300></less-300>
        </div>
    </div>
</template>

<script>
    import NavBar from './components/NavBar.vue';
    import Less300 from './components/Less300.vue';

    export default {
        components: {
            NavBar,
            Less300
        },
        watch: {
            $route: {
                handler (route) {
                    document.title = route.meta.title;
                },
                deep: true,
                immediate: true
            }
        }
    }
</script>
