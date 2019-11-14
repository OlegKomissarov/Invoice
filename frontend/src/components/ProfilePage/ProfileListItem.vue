<template>
    <div class="profile-item">
        <div class="profile-item-with-button">
            <input class="profile-item__elem profile-item__elem_field"
                   placeholder="Title"
                   v-model="profileAttribute.field"
                   @click="$refs.field.select()"
                   ref="field"
                   :disabled="!profileAttribute.isAllowToChange"
                   @blur="$emit('update')"
            />
            <div class="remove-button"
                 v-show="profileAttribute.isAllowToChange"
                 @click="$emit('delete')"
            >
                X
            </div>
        </div>

        <input class="profile-item__elem profile-item__elem_value"
               placeholder="Description"
               v-model="profileAttribute.value"
               @click="$refs.value.select()"
               ref="value"
               @blur="$emit('update')"
        />

    </div>
</template>

<script>
    export default {
        props: {
            profileAttribute: Object
        }
    };
</script>
