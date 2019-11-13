import ProfileApi from '../api/ProfileApi';

export default {
    computed: {
        config () {
            return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
        }
    },
    methods: {
        storeNewProfile () {
            ProfileApi.store(this.config)
                .then(() => {
                    this.$router.push({ name: 'profile:show' });
                })
                .catch(err => {
                    this.$toasted.error('Whoops. Something went wrong: ' + err);
                });
        },
        updateVuex (profileId) {
            this.$store.commit('setProfileId', profileId);
        }
    }
}